package com.AppointentManagementSystem.ProjectBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer recordId;

    private String username;

    private String doctorName;

    private String date;

    private String time;

    private boolean completed;

}
