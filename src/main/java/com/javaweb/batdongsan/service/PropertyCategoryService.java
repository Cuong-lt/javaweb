package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.property_category.PropertyCategoryRequest;
import com.javaweb.batdongsan.model.response.property_category.PropertyCategoryResponse;

import java.util.List;

public interface PropertyCategoryService {
    PropertyCategoryResponse create(PropertyCategoryRequest request);

    List<PropertyCategoryResponse> getAll();

    PropertyCategoryResponse getById(Long id);

    PropertyCategoryResponse update(Long id, PropertyCategoryRequest request);

    void delete(Long id);
}
