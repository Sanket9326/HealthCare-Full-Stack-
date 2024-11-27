package com.AppointentManagementSystem.ProjectBackend.Controller;

import com.AppointentManagementSystem.ProjectBackend.Models.User;
import com.AppointentManagementSystem.ProjectBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins ="http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping ("login")
    public ResponseEntity<String> login(@RequestParam String username,@RequestParam String password) {
        return userService.login(username,password);
    }

    @PutMapping("signup")
    @ResponseBody
    public  ResponseEntity<String> signUp(@RequestBody Map<String, String> data) {
        User user = new User();
        user.setEmail(data.get("email"));
        user.setPassword(data.get("password"));
        user.setFirstName(data.get("firstName"));
        user.setLastName(data.get("lastName"));
        user.setMobileNumber(data.get("mobileNumber"));
        return userService.registerUser(user);
    }

}
