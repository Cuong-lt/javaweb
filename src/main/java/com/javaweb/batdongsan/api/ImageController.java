package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.image.ImageResponse;
import com.javaweb.batdongsan.service.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageController {

    ImageService imageService;

    @PostMapping(value = "/property/{propertyId}", consumes = "multipart/form-data")
    public ApiResponse<ImageResponse> uploadImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long propertyId) {
        return ApiResponse.<ImageResponse>builder()
                .result(imageService.uploadImage(file, propertyId))
                .message("Image uploaded successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ImageResponse> getImage(@PathVariable Long id) {
        return ApiResponse.<ImageResponse>builder()
                .result(imageService.getImage(id))
                .message("Get image successfully")
                .build();
    }

    @GetMapping("/property/{propertyId}")
    public ApiResponse<List<ImageResponse>> getImagesByProperty(@PathVariable Long propertyId) {
        return ApiResponse.<List<ImageResponse>>builder()
                .result(imageService.getImagesByProperty(propertyId))
                .message("Get images by property successfully")
                .build();
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ApiResponse<ImageResponse> updateImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        return ApiResponse.<ImageResponse>builder()
                .result(imageService.updateImage(id, file))
                .message("Image updated successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ApiResponse.<String>builder()
                .message("Image deleted successfully")
                .build();
    }
}
