package org.example.q2classes.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Instructor {

    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z]{2,15}$", message = "Name must be only letters, 2 to 15 characters")
    private String name;

    @NotEmpty(message = "ID is required")
    @Pattern(regexp = "^\\d{9}$", message = "ID must be exactly 9 digits")
    private String id;

    @NotEmpty(message = "Faculty is required")
    @Pattern(regexp = "^[A-Za-z]{4,30}$", message = "Faculty must be only letters, 4 to 30 characters")
    private String faculty;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\+9665\\d{8}$", message = "Phone must start with +9665 and be followed by 8 digits")
    private String phoneNumber;

    @NotEmpty(message = "Major is required")
    @Pattern(regexp = "^[A-Za-z]{4,30}$", message = "Major must be only letters, 4 to 30 characters")
    private String major;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Office number is required")
    @Pattern(regexp = "^\\d{3}$", message = "Office number must be exactly 3 digits")
    private String office;
}
