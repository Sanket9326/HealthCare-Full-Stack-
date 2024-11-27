package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Models.Appointment;
import com.AppointentManagementSystem.ProjectBackend.Models.Records;
import com.AppointentManagementSystem.ProjectBackend.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PutMapping("add")
    public ResponseEntity<Map<String,String>> addAppointment(@RequestBody Map<String, String> data) {
        return appointmentService.addAppointment(data);
    }

    @GetMapping("history")
    public ResponseEntity<List<Records>> getAppointmentHistory(@RequestParam String username) {
        return appointmentService.getAppointmentHistory(username);
    }

    @GetMapping("current")
    public  ResponseEntity<List<Appointment>> getCurrAppointments(@RequestParam String username) {
        return appointmentService.getCurrAppointments(username);
    }

    @DeleteMapping("cancel")
    public ResponseEntity<Map<String,String>> deleteAppointment(@RequestParam Integer appointmentId,@RequestParam String doctorName,@RequestParam String date,@RequestParam String time,@RequestParam String username) {
        return appointmentService.deleteAppointment(appointmentId,doctorName,date,time,username);
    }

    @GetMapping("all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @DeleteMapping("remove")
    public ResponseEntity<String> removeAppointment(@RequestParam Integer appointmentId) {
        return appointmentService.removeAppointment(appointmentId);
    }
}
