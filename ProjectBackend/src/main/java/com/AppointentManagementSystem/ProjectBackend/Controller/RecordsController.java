package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("record")
@CrossOrigin(origins = "http://localhost:4200")
public class RecordsController {

    @Autowired
    RecordService recordService;

    @PostMapping("add")
    public ResponseEntity<String> addRecord(@RequestBody Map<String,String> data){
        return recordService.addRecord(data);
    }

}
