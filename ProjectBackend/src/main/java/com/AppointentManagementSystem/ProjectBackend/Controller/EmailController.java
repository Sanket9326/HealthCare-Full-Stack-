package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return emailService.forgotPassword(email);
    }
}
