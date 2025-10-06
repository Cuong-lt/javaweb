package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.model.request.property_status.PropertyStatusRequest;
import com.javaweb.batdongsan.model.response.property_status.PropertyStatusResponse;

import java.util.List;

public interface PropertyStatusService {
    PropertyStatusResponse create(PropertyStatusRequest request);

    List<PropertyStatusResponse> getAll();

    PropertyStatusResponse getById(Long id);

    PropertyStatusResponse update(Long id, PropertyStatusRequest request);

    void delete(Long id);
}
