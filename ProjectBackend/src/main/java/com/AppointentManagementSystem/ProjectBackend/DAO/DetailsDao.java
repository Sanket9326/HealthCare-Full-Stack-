package com.AppointentManagementSystem.ProjectBackend.DAO;

import com.AppointentManagementSystem.ProjectBackend.Models.Details;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsDao extends JpaRepository<Details, Integer> {


    @Query(value = "SELECT * FROM Details", nativeQuery = true)
    List<Details> getAllData();


    @Transactional
    @Modifying
    @Query(value = "UPDATE Details SET available_time = :newTime WHERE details_id = :id", nativeQuery = true)
    void updateTime(@Param("id") int id, @Param("newTime") String availableTime);

}
