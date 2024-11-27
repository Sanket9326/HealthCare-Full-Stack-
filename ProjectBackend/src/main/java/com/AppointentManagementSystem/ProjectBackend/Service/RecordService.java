package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.RecordsDao;
import com.AppointentManagementSystem.ProjectBackend.Models.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecordService {

    @Autowired
    RecordsDao recordsDao;

    @Autowired
    EmailService emailService;

    public ResponseEntity<String> addRecord(Map<String, String> data) {
        try {
            Records record = new Records();
            record.setUsername(data.get("username"));
            record.setDoctorName(data.get("doctorName"));
            record.setDate(data.get("date"));
            record.setTime(data.get("time"));
            if (data.get("completed").equals("true")) {
                record.setCompleted(true);
                emailService.appointmentCompleted(data.get("username"), data.get("doctorName"), data.get("time"));
            } else {
                record.setCompleted(false);
                emailService.appointmentCancelled(data.get("username"), data.get("doctorName"), data.get("time"));
            }
            recordsDao.save(record);
            return new ResponseEntity<>("Record added", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
