package org.example.employeemanagement.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    //ID , name, email , phoneNumber ,age, position, onLeave, hireDate and annualLeave.
    @NotEmpty
    @Size(min = 2, message = "id must have two chars length")
    private String id;

    @NotEmpty
    @Size(min = 4, message = "name must have four chars length")
    @Pattern(regexp = "[A-Za-z]+", message = "name must contains only chars")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^05[\\d]{8}$", message = "phone number must start with 05 and contains 10 digits")
    private String phoneNumber;

    @NotNull
    @Positive (message = "you cannot enter negative number")
    @Min(value = 25, message = "age at least 25")
    private int age;

    @NotEmpty
    @Pattern(regexp = "(supervisor|coordinator)", message = "position Must be either \"supervisor\" or \"coordinator\" only")
    private String position;

    @NotEmpty
    @Pattern(regexp = "(false|true)")
    private String onLeave;

    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime hireDate;

    @NotNull
    @Positive
    private int annualLeave;

}
