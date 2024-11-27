package com.AppointentManagementSystem.ProjectBackend.Service;

import com.AppointentManagementSystem.ProjectBackend.DAO.AdminDao;
import com.AppointentManagementSystem.ProjectBackend.Models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public ResponseEntity<String> login(String username, String password) {
        try{
            List<Admin> curr = adminDao.isValid(username,password);
            if (curr.isEmpty()){
                return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Try Again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> registerAdmin(Admin admin) {
        try{
            List<Admin> curr = adminDao.isValid(admin.getAdminEmail(),admin.getAdminPassword());
            if (!curr.isEmpty()){
                return  new ResponseEntity<>("Admin already exists", HttpStatus.CONFLICT);
            }
            adminDao.save(admin);
            return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Try Again!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
