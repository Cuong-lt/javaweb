package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.request.property.PropertyUpdateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface PropertyService {
    PropertyResponse createProperty(@Valid PropertyCreateRequest request);

    PropertyResponse getById(Long id);

    List<PropertyResponse> getAll();

    PropertyResponse update(Long id, @Valid PropertyUpdateRequest request);

    Void deleteById(Long id);


}
