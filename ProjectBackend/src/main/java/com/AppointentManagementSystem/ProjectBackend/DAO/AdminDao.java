package com.AppointentManagementSystem.ProjectBackend.DAO;

import com.AppointentManagementSystem.ProjectBackend.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

    @Query(value = "SELECT * FROM Admin WHERE admin_email = :username AND admin_password = :password",nativeQuery = true)
    List<Admin> isValid(@Param("username") String username,@Param("password") String password);
}
