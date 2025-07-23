package org.example.q2classes.Entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Housing {

    @NotEmpty(message = "Building number is required")
    @Pattern(regexp = "^\\d{2}$", message = "Building number must be exactly 2 digits")
    private String buildingNumber;

    @NotEmpty(message = "Room number is required")
    @Pattern(regexp = "^\\d{3}$", message = "Room number must be exactly 3 digits")
    private String roomNumber;

    @NotEmpty(message = "Price is required")
    @Pattern(regexp = "^(1000|2000)$", message = "Price must be 1000 or 2000")
    private String price;

    @NotEmpty(message = "Type is required")
    @Pattern(regexp = "^(single|roommate)$", message = "Must be either 'single' or 'roommate'")
    private String type;
}
