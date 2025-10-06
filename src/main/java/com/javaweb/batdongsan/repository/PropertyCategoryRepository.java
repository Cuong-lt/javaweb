package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.PropertyCategory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyCategoryRepository extends JpaRepository<PropertyCategory, Long> {
    boolean existsByName(String name);

    Optional<PropertyCategory> findByName(@NotBlank(message = "category must not be blanked") String category);
}
