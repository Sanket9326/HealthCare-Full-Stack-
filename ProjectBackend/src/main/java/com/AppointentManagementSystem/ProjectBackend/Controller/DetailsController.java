package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Models.Details;
import com.AppointentManagementSystem.ProjectBackend.Service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("details")
@CrossOrigin(origins = "http://localhost:4200")
public class DetailsController {

    @Autowired
    DetailsService detailsService;

    @GetMapping("get")
    public ResponseEntity<List<Details>> getAllData(){
        return detailsService.getAllData();
    }

    @PutMapping("add")
    public ResponseEntity<Map<String,String>> addDetails(@RequestParam String doctorName, @RequestParam String speciality, @RequestParam String availableTime){
        return detailsService.addDetails(doctorName,speciality,availableTime);
    }

    @DeleteMapping("remove")
    public ResponseEntity<Map<String,String>> removeDetails(@RequestParam String doctorId){
        return detailsService.removeDetails(doctorId);
    }

    @PostMapping("update")
    public ResponseEntity<Map<String,String>> updateDetails(@RequestParam String doctorId,@RequestParam String availableTime){
        return detailsService.updateDetails(doctorId,availableTime);
    }
}
