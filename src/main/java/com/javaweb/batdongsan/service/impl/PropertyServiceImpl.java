package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.PropertyConverter;
import com.javaweb.batdongsan.entity.Property;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.request.property.PropertyUpdateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.repository.*;
import com.javaweb.batdongsan.service.PropertyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PropertyResponse getById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        PropertyResponse response = converter.toResponse(property);
        return response;
    }

    @Override
    public List<PropertyResponse> getAll() {
        List<Property> properties = propertyRepository.findAll();
        List<PropertyResponse> propertyResponseList = properties.stream()
                .map((property) -> converter.toResponse(property))
                .toList();
        return propertyResponseList;
    }

    @Override
    public PropertyResponse update(Long id, PropertyUpdateRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        property = converter.toEntity(property,request);
        propertyRepository.save(property);
        return converter.toResponse(property);
    }

    @Override
    public Void deleteById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROPERTY_NOT_FOUND));
        propertyRepository.deleteById(id);
        return null;
    }
}
