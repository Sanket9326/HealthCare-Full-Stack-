package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Models.Admin;
import com.AppointentManagementSystem.ProjectBackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins ="http://localhost:4200")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestParam String username,@RequestParam String password) {
        return adminService.login(username,password);
    }

    @PutMapping("signup")
    public ResponseEntity<String> adminSignUp(@RequestBody Map<String,String> data){
        Admin admin = new Admin();
        admin.setAdminEmail(data.get("email"));
        admin.setAdminPassword(data.get("password"));
        admin.setAdminName(data.get("name"));
        admin.setMobileNumber(data.get("mobileNumber"));
        return adminService.registerAdmin(admin);
    }
}
