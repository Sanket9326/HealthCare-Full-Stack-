package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserDao userDao;

    public ResponseEntity<String> forgotPassword(String email) {
        try{
             SimpleMailMessage message = new SimpleMailMessage();
             message.setTo(email);
             message.setFrom("Healthcare+");
             message.setSubject("Healthcare password recovery");
             String password = userDao.getPasswordByEmail(email);
             if (password == null){
                 return new ResponseEntity<>("Invalid Email", HttpStatus.UNAUTHORIZED);
             }
             message.setText("Your password for healthcare+ login is : " + password);
             mailSender.send(message);
             return new ResponseEntity<>("Check mail for password", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> sendAppointmentConfirmationEmail(String email,String date,String time,String doctorName) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Appointment confirmation");
            message.setTo(email);
            message.setFrom("appointment@appointentmanagementsystem");
            String msg = "Welcome to Healthcare+ \n" +
                    "Your appointment for Date: " + date + ", Time: " + time + ", Doctor: " + doctorName +
                    " has been confirmed. Please be on time.\n" +
                    "Thank you.";
            message.setText(msg);
            mailSender.send(message);
            return new ResponseEntity<>("Appointment confirmed", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> appointmentCancellationEmail(String username,String doctorName,String date,String time) {
        try {
             SimpleMailMessage message = new SimpleMailMessage();
             message.setSubject("Appointment cancellation");
             message.setTo(username);
             message.setFrom("appointment@appointentmanagementsystem");
             String msg = "Appointment for username : "+  username
                     + " of doctor " + doctorName + " for date " + date + " and time " + time
                     + " has been cancelled successfully on your request ! Thank you." ;
             message.setText(msg);
             mailSender.send(message);
             return new ResponseEntity<>("Appointment canceled", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> signupEmail(String email){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("New Registration");
            message.setTo(email);
            String msg = "Welcome to HealthCare+ , your account has been successfully created \n" +
                    " Now you can use services \n " +
                    "Thank you.";
            message.setText(msg);
            mailSender.send(message);
            return new ResponseEntity<>("New Registration added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> appointmentCancelled(String username ,String doctorName,String date) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Appointment cancellation");
            message.setTo(username);
            message.setFrom("appointment@appointentmanagementsystem");
            String text = "Hello User \n" +
                    "Your appointment for " + doctorName + "on " + date + " has been cancelled due to your absence! \n"
                    +"Thank you.";
            message.setText(text);
            mailSender.send(message);
            return new ResponseEntity<>("Appointment canceled", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> appointmentCompleted(String username, String doctorName, String date) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Appointment completed");
            message.setTo(username);
            message.setFrom("appointment@appointentmanagementsystem");
            String text = "Hello User \n" +
                    "Your appointment for " + doctorName + "on " + date + " has been completed! \n" +
                    "Thank you.";
            message.setText(text);
            mailSender.send(message);
            return new ResponseEntity<>("Appointment completed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
