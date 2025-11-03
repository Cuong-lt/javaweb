package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.response.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageResponse uploadImage(MultipartFile file, Long propertyId);
    ImageResponse getImage(Long id);
    List<ImageResponse> getImagesByProperty(Long propertyId);
    ImageResponse updateImage(Long id, MultipartFile file);
    void deleteImage(Long id);
}
