package com.AppointentManagementSystem.ProjectBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer appointmentId;

    private String username;

    private String doctorName;

    private String availableTime;

    private String appointmentDate;

    private String appointmentTime;
}
