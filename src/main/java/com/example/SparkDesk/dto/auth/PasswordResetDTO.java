package com.example.SparkDesk.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordResetDTO {
    @Email @NotBlank private String email;
    @NotBlank @Size(min=6) private String newPassword;
}
