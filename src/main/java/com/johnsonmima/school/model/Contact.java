package com.johnsonmima.school.model;
import lombok.Data;

@Data
public class Contact {
    private String name;
    private String phone;
    private String subject;
    private String email;
    private String message;
}
