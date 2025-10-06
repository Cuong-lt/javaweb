package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyConverter;
import com.javaweb.batdongsan.entity.Property;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.repository.*;
import com.javaweb.batdongsan.service.PropertyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl implements PropertyService {

    PropertyConverter converter;
    PropertyRepository propertyRepository;

    @Override
    public PropertyResponse createProperty(PropertyCreateRequest request) {
        Property property = converter.toEntity(request);
        if(propertyRepository.existsByAddress(property.getAddress())){
            throw new AppException(ErrorCode.PROPERTY_EXISTED);
        }
        propertyRepository.save(property);
        return converter.toResponse(property);
    }
}
