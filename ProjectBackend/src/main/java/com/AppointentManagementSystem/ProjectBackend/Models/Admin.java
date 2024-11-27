package com.AppointentManagementSystem.ProjectBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @NotEmpty(message = "Admin Name cannot be empty!")
    private String adminName;

    @Email(message = "Enter valid email !")
    @NotEmpty(message = "Email cannot be empty!")
    private String adminEmail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8,max = 8,message = "password length should be 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase letter, and one special character")
    private String adminPassword;

    @NotEmpty(message = "Mobile number cannot be empty!")
    @Size(min = 10,max = 10,message = "Enter valid mobile number!")
    private String mobileNumber;
}
