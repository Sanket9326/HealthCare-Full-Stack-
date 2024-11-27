package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.DetailsDao;
import com.AppointentManagementSystem.ProjectBackend.Models.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailsService {

    @Autowired
    DetailsDao detailsDao;

    public ResponseEntity<List<Details>> getAllData() {
        try{
            List <Details> allData = detailsDao.getAllData();
            return new ResponseEntity<>(allData, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String,String>> addDetails(String doctorName, String speciality, String availableTime) {
        try {
            Details detail = new Details();
            detail.setDoctorName(doctorName);
            detail.setSpeciality(speciality);
            detail.setAvailableTime(availableTime);
            detailsDao.save(detail);
            Map<String,String> response = new HashMap<>();
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            Map<String,String> response = new HashMap<>();
            response.put("status", "Try Again!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String,String>> removeDetails(String doctorId) {
        try{
            detailsDao.deleteById(Integer.parseInt(doctorId));
            Map<String,String> response = new HashMap<>();
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            Map<String,String> response = new HashMap<>();
            response.put("status", "Try Again!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String,String>> updateDetails(String doctorId, String availableTime) {
        try {
            System.out.println(doctorId +"  :"+availableTime+":");
            detailsDao.updateTime(Integer.parseInt(doctorId),availableTime);
            Map<String,String> response = new HashMap<>();
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            Map<String,String> response = new HashMap<>();
            response.put("status", "Try Again!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
