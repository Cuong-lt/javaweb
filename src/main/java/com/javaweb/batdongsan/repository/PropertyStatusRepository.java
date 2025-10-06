package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.PropertyStatus;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyStatusRepository extends JpaRepository<PropertyStatus,Long> {
    boolean existsByName(String name);

    Optional<PropertyStatus> findByName(String status);
}
