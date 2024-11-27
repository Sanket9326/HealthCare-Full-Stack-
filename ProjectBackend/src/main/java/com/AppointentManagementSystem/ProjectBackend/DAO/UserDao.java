package com.AppointentManagementSystem.ProjectBackend.DAO;

import com.AppointentManagementSystem.ProjectBackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Query(value = "Select * FROM User WHERE email = :username AND password = :password",nativeQuery = true)
    List<User> isValid(@Param("username") String username,@Param("password") String password);

    @Query(value = "Select password FROM User WHERE email = :email",nativeQuery = true)
    String getPasswordByEmail(@Param("email") String email);
}
