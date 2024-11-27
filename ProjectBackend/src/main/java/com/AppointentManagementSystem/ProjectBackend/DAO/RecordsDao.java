package com.AppointentManagementSystem.ProjectBackend.DAO;

import java.util.List;
import com.AppointentManagementSystem.ProjectBackend.Models.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsDao extends JpaRepository<Records, Integer> {

    @Query(value = "SELECT * FROM Records Where username = :username ",nativeQuery = true)
    List<Records> getRecords(@Param("username") String username);

}
