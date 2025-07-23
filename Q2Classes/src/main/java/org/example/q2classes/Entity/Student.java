package org.example.q2classes.Entity;

import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {

    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z]{2,15}$", message = "Name must be only letters, 2 to 15 characters")
    private String name;

    @NotEmpty(message = "ID is required")
    @Pattern(regexp = "^\\d{9}$", message = "ID must be exactly 9 digits")
    @Pattern(regexp = "^\\d{2}[1-2]90\\d{4}$", message = "ID must follow format: YY+Semester+90+XXXX")
    private String id;

    @NotEmpty(message = "Major is required")
    @Pattern(regexp = "^[A-Za-z]{4,30}$", message = "Major must be only letters, 4 to 30 characters")
    private String major;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\+9665\\d{8}$", message = "Phone must start with +9665 and be followed by 8 digits")
    private String phoneNumber;

    @NotNull(message = "GPA is required")
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "4.0", message = "GPA must be at most 4.0")
    private Double GPA;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Join date is required")
    @PastOrPresent(message = "Join date must be in the past or present")
    private LocalDate joinDate;
}
