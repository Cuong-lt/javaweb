package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.Image;
import com.javaweb.batdongsan.entity.Property;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.response.image.ImageResponse;
import com.javaweb.batdongsan.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {
    @Autowired
    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;

    public ImageResponse toResponse(Image image) {
        ImageResponse response = modelMapper.map(image, ImageResponse.class);
        response.setPropertyId(image.getProperty() != null ? image.getProperty().getId() : null);
        return response;
    }
}
