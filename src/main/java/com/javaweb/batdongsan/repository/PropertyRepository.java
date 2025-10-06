package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
    boolean existsByAddress(String address);
}
