package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.ImageConverter;
import com.javaweb.batdongsan.entity.Image;
import com.javaweb.batdongsan.entity.Property;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.response.image.ImageResponse;
import com.javaweb.batdongsan.repository.ImageRepository;
import com.javaweb.batdongsan.repository.PropertyRepository;
import com.javaweb.batdongsan.service.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageServiceImpl implements ImageService {
    ImageRepository imageRepository;
    PropertyRepository propertyRepository;
    ImageConverter imageConverter;


    @Override
    public ImageResponse uploadImage(MultipartFile file, Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));

        String uploadDir = "uploads/images/";
        String originalFilename = file.getOriginalFilename();

        // get random id from UUID
        String randomID = UUID.randomUUID().toString();

        // create file name with random id
        String fileName = randomID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        Path filePath = Paths.get(uploadDir + fileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }

        Image image = new Image();
        image.setImageUrl("/" + uploadDir + fileName);
        image.setProperty(property);
        imageRepository.save(image);

        return imageConverter.toResponse(image);
    }
    @Override
    public ImageResponse getImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));
        return imageConverter.toResponse(image);
    }

    @Override
    public List<ImageResponse> getImagesByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        List<Image> images = imageRepository.findByPropertyId(propertyId);
        List<ImageResponse> imageResponseList = images.stream().map(imageConverter::toResponse).toList();
        return imageResponseList;
    }

    @Override
    public ImageResponse updateImage(Long id, MultipartFile file) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

        String uploadDir = "uploads/images/";
        String originalFilename = file.getOriginalFilename();

        // get random id from UUID
        String randomID = UUID.randomUUID().toString();

        // create file name with random id
        String fileName = randomID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        Path filePath = Paths.get(uploadDir + fileName);

        try {
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error updating file: " + e.getMessage());
        }

        image.setImageUrl("/uploads/images/" + fileName);
        imageRepository.save(image);

        return imageConverter.toResponse(image);
    }

    @Override
    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

        try {
            Path path = Paths.get(image.getImageUrl().replaceFirst("/", ""));
            Files.deleteIfExists(path);
//            String projectRoot = System.getProperty("user.dir");
//
//            // Đường dẫn thư mục lưu ảnh
//            String uploadDir = projectRoot + File.separator + "uploads" + File.separator + "images";
//
//            // Lấy tên file (ví dụ: abc123.png) từ đường dẫn ảnh trong DB
//            String fileName = Paths.get(image.getImageUrl()).getFileName().toString();
//
//            // Tạo đường dẫn tuyệt đối tới file
//            Path filePath = Paths.get(uploadDir, fileName);
//
//            // Xóa file nếu tồn tại
//            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {}

        imageRepository.delete(image);
    }
}
