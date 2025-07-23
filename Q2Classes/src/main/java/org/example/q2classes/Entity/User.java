package org.example.q2classes.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {

    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^\\d{9}$", message = "Username must be a 9-digit ID")
    private String username;

    @NotEmpty(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain uppercase, lowercase, digit, special character, and be at least 8 characters"
    )
    private String password;

    @NotEmpty(message = "Status is required")
    @Pattern(
            regexp = "^(Active|Graduate|not-active)$",
            message = "Status must be either Active, Graduate, or not-active"
    )
    private String status;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Role is required")
    @Pattern(
            regexp = "^(Admin|Student|Instructor)$",
            message = "Role must be either Admin, Student, or Instructor"
    )
    private String role;
}

