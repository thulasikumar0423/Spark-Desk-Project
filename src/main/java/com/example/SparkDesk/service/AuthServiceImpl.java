package com.example.SparkDesk.service;

import com.example.SparkDesk.dto.auth.RegisterRequestDTO;
import com.example.SparkDesk.dto.auth.LoginRequestDTO;
import com.example.SparkDesk.dto.auth.PasswordResetDTO;
import com.example.SparkDesk.dto.user.UserResponseDTO;
import com.example.SparkDesk.exception.InvalidPasswordException;
import com.example.SparkDesk.exception.RoleAccessDeniedException;
import com.example.SparkDesk.exception.UserNotFoundException;
import com.example.SparkDesk.model.PasswordHistory;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.PasswordHistoryRepository;
import com.example.SparkDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository = null;
    private final PasswordHistoryRepository passwordHistoryRepository = null;
    private final PasswordEncoder passwordEncoder = null;
    private final AuthenticationManager authenticationManager = null;

    private static final int PASSWORD_HISTORY_LIMIT = 3;

    @Override
    public UserResponseDTO register(RegisterRequestDTO dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole() != null ? dto.getRole() : User.Role.TEAM_MEMBER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        // Save password history
        PasswordHistory ph = new PasswordHistory();
        ph.setUser(user);
        ph.setPasswordHash(user.getPassword());
        passwordHistoryRepository.save(ph);

        return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }

    @Override
    public void login(LoginRequestDTO dto, HttpServletRequest request) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getRole().equals(dto.getRole())) {
            throw new RoleAccessDeniedException("Role mismatch for login");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        // Session is handled by Spring Security automatically on successful authentication
    }

    @Override
    public void resetPassword(PasswordResetDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Check password history
        List<PasswordHistory> recentPasswords = passwordHistoryRepository
                .findByUserOrderByCreatedAtDesc(user, org.springframework.data.domain.PageRequest.of(0, PASSWORD_HISTORY_LIMIT));

        for (PasswordHistory ph : recentPasswords) {
            if (passwordEncoder.matches(dto.getNewPassword(), ph.getPasswordHash())) {
                throw new InvalidPasswordException("New password cannot match any of the previous " + PASSWORD_HISTORY_LIMIT + " passwords");
            }
        }

        // Update password
        String encoded = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(encoded);
        userRepository.save(user);

        PasswordHistory ph = new PasswordHistory();
        ph.setUser(user);
        ph.setPasswordHash(encoded);
        passwordHistoryRepository.save(ph);
    }
}
