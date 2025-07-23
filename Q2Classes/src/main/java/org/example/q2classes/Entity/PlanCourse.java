package org.example.q2classes.Entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PlanCourse {

    @NotEmpty(message = "Course code is required")
    @Pattern(regexp = "^[A-Z]{2,5} \\d{3}$", message = "Course code must be like 'CS 101'")
    private String courseCode;

    @NotEmpty(message = "Course name is required")
    @Pattern(regexp = "^[A-Za-z ]{5,20}$", message = "Course name must be 5 to 20 alphabetic characters")
    private String courseName;

    @Min(value = 1, message = "Credit hours must be between 1 and 4")
    @Max(value = 4, message = "Credit hours must be between 1 and 4")
    private int creditHours;
}

