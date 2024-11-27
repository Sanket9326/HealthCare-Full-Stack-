package com.AppointentManagementSystem.ProjectBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer detailsId;

    private String doctorName;

    private String speciality;

    private String availableTime;

}
