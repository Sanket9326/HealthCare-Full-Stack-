package com.AppointentManagementSystem.ProjectBackend.DAO;

import com.AppointentManagementSystem.ProjectBackend.Models.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {
    @Query(value = "SELECT * FROM Appointment WHERE doctor_name = :name AND appointment_date = :date AND appointment_time = :time",nativeQuery = true)
    List<String> isPossible(@Param("name") String doctorName, @Param("date") String appointmentDate, @Param("time") String appointmentTime);

    @Query(value = "SELECT * FROM Appointment WHERE username = :user",nativeQuery = true)
    List<Appointment> getCurrAppointments(@Param("user") String username);

    @Query(value = "SELECT * FROM Appointment",nativeQuery = true)
    List<Appointment> getAllAppointments();
}
