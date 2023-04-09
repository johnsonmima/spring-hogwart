package com.johnsonmima.school.model;
import jakarta.validation.constraints.*;
import lombok.Data;

//@NotNull : Check if a field is not null but allow empty value & zero elements
//@NotEmpty: not null and size/length is greater than zero
//@NotBlank: not null & trim length is greater than zero

@Data
public class Contact {

    @NotBlank(message = "Name is required!")
    @Size(min = 3, message = "Name must be more than 3 character long!")
    private String name;

    @NotBlank(message = "Mobile number is required!")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 " +
            "digits")
    private String phone;

    @NotBlank(message = "Subject is required!")
    @Size(min = 3, message = "Subject must be more than 3 character long!")
    private String subject;

    @NotBlank(message = "Email is required!")
    @Email(message = "Enter a valid email!")
    private String email;

    @NotBlank(message = "Message is required!")
    @Size(min = 10, message = "Message must be more than 10 character long!")
    private String message;

}
