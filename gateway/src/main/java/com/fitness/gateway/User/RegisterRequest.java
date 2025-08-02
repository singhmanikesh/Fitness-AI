package com.fitness.gateway.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String keycloakId;
    @NotBlank(message = "password is required")
    @Size(min=6, message = "Password must be at least 6 characters long")
    private String password;
    private String firstName;
    private String lastName;
}
