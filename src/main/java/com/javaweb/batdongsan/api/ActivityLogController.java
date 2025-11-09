package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.entity.ActivityLog;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("")
    public ApiResponse<List<ActivityLog>> getAll(){
        ApiResponse<List<ActivityLog>> response = new ApiResponse<>();
        response.setResult(activityLogService.getAll());
        return response;
    }
}
