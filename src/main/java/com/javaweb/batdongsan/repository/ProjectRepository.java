package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository  extends JpaRepository<Project,Long> {
    @Query("SELECT COUNT(p.id) FROM Project p")
    Long totalProjects();

    @Query("SELECT p FROM Project p WHERE p.endDate BETWEEN :today AND :upcomingDate")
    List<Project> findProjectsAboutToExpire(
            @Param("today") LocalDate today,
            @Param("upcomingDate") LocalDate upcomingDate
    );
}
