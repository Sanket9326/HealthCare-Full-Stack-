package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.AppointmentDao;
import com.AppointentManagementSystem.ProjectBackend.DAO.RecordsDao;
import com.AppointentManagementSystem.ProjectBackend.Models.Appointment;
import com.AppointentManagementSystem.ProjectBackend.Models.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RecordsDao recordsDao;

    public ResponseEntity<Map<String,String>> addAppointment(Map<String, String> data) {
        try {
            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(data.get("appointmentDate"));
            appointment.setAppointmentTime(data.get("appointmentTime"));
            appointment.setUsername(data.get("username"));
            appointment.setDoctorName(data.get("doctorName"));
            appointment.setAvailableTime(data.get("availableTime"));
            List<String> records = appointmentDao.isPossible(data.get("doctorName"),data.get("appointmentDate"),data.get("appointmentTime"));

            if (!records.isEmpty()){
                Map <String,String> response = new HashMap<>();
                response.put("message", "Conflict with appointment, Choose another date or time");
                return new ResponseEntity<>(response,HttpStatus.CONFLICT);
            }
            appointmentDao.save(appointment);
            emailService.sendAppointmentConfirmationEmail(data.get("username"),data.get("appointmentDate"),data.get("appointmentTime"),data.get("doctorName"));
            Map <String,String> response = new HashMap<>();
            response.put("message", "appointment added successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            Map <String,String> response = new HashMap<>();
            response.put("message", "Try again!");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Records>> getAppointmentHistory(String username) {
        try{
            List<Records> records = recordsDao.getRecords(username);
            return new ResponseEntity<>(records,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<Appointment>> getCurrAppointments(String username) {
        try{
            List<Appointment> currAppointments = appointmentDao.getCurrAppointments(username);
            return new ResponseEntity<>(currAppointments,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String,String>> deleteAppointment(Integer appointmentId, String doctorName,String date,String time,String username) {
        try{
            appointmentDao.deleteById(appointmentId);
            emailService.appointmentCancellationEmail(username,doctorName,date,time);
            Map <String,String> response = new HashMap<>();
            response.put("message", "appointment deleted successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            Map <String,String> response = new HashMap<>();
            response.put("message", "Try again!");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try{
            List<Appointment> currAppointments = appointmentDao.getAllAppointments();
            return new ResponseEntity<>(currAppointments,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeAppointment(Integer appointmentId) {
        try{
            appointmentDao.deleteById(appointmentId);
            return new ResponseEntity<>("appointment removed successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Try Again!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
