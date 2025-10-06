package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.PropertyType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType,Long> {
    boolean existsByName(String name);

    Optional<PropertyType> findByName( String type);
}
