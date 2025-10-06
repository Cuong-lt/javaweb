package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.model.request.property_status.PropertyStatusRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.property_status.PropertyStatusResponse;
import com.javaweb.batdongsan.service.PropertyStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property-statuses")
@RequiredArgsConstructor
public class PropertyStatusController {
    private final PropertyStatusService propertyStatusService;

    @PostMapping("/create")
    public ApiResponse<PropertyStatusResponse> create(@RequestBody @Valid PropertyStatusRequest request) {
        ApiResponse<PropertyStatusResponse> response = new ApiResponse<>();
        response.setResult(propertyStatusService.create(request));
        response.setMessage("Property status created successfully");
        return response;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<PropertyStatusResponse>> getAll() {
        ApiResponse<List<PropertyStatusResponse>> response = new ApiResponse<>();
        response.setResult(propertyStatusService.getAll());
        return response;
    }

    @GetMapping("/getById/{id}")
    public ApiResponse<PropertyStatusResponse> getById(@PathVariable Long id) {
        ApiResponse<PropertyStatusResponse> response = new ApiResponse<>();
        response.setResult(propertyStatusService.getById(id));
        return response;
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PropertyStatusResponse> update(@PathVariable Long id,
                                                      @RequestBody @Valid PropertyStatusRequest request) {
        ApiResponse<PropertyStatusResponse> response = new ApiResponse<>();
        response.setResult(propertyStatusService.update(id, request));
        response.setMessage("Property status updated successfully");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        ApiResponse<String> response = new ApiResponse<>();
        propertyStatusService.delete(id);
        response.setResult("Property status deleted successfully");
        return response;
    }
}
