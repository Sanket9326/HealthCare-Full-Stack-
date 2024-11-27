package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.UserDao;
import com.AppointentManagementSystem.ProjectBackend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<String> login(String username, String password) {
        try{
            List<User> curr = userDao.isValid(username,password);
            if (curr.isEmpty()){
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
            System.out.println(username +" "+password);
            return new ResponseEntity<>("Successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> registerUser(User user) {
        try {
            List<User> curr = userDao.isValid(user.getEmail(),user.getPassword());
            if (!curr.isEmpty()){
                return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            }
            emailService.signupEmail(user.getEmail());
            userDao.save(user);
            return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Try Again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
