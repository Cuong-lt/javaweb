package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import jakarta.validation.Valid;

public interface PropertyService {
    PropertyResponse createProperty(@Valid PropertyCreateRequest request);
}
