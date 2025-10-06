package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.property_type.PropertyTypeRequest;
import com.javaweb.batdongsan.model.response.property_type.PropertyTypeResponse;

import java.util.List;

public interface PropertyTypeService {
    PropertyTypeResponse create(PropertyTypeRequest request);

    List<PropertyTypeResponse> getAll();

    PropertyTypeResponse getById(Long id);

    PropertyTypeResponse update(Long id, PropertyTypeRequest request);

    void delete(Long id);
}
