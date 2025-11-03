package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository  extends JpaRepository<Image,Long> {
    List<Image> findByPropertyId(Long propertyId);
}
