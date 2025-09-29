package com.example.SparkDesk.config;

import com.example.SparkDesk.model.Role;
import com.example.SparkDesk.model.User;
import com.example.SparkDesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        createUser("admin@sparkdesk.local", "Admin User", "Password123", Role.ADMIN);
        createUser("manager@sparkdesk.local", "Manager User", "Password123", Role.MANAGER);
        createUser("lead@sparkdesk.local", "Team Lead", "Password123", Role.TEAM_LEAD);
        createUser("member@sparkdesk.local", "Team Member", "Password123", Role.TEAM_MEMBER);
        createUser("it@sparkdesk.local", "IT Desk", "Password123", Role.IT_DESK);
    }

    private void createUser(String email, String fullName, String rawPassword, Role role) {
        if (userRepository.findByEmail(email).isPresent()) return;
        User u = new User();
        u.setEmail(email);
        u.setFullName(fullName);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRole(role);
        u.setEnabled(true);
        userRepository.save(u);
    }
}
