package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.property.PropertyCreateRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.property.PropertyResponse;
import com.javaweb.batdongsan.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/create")
    public ApiResponse<PropertyResponse> createProperty(@RequestBody @Valid PropertyCreateRequest request){
            ApiResponse<PropertyResponse> response = new ApiResponse<>();
            response.setResult(propertyService.createProperty(request));
            response.setMessage("property created successfully");
            return response;
    }
}
