package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.entity.ActivityLog;

import java.util.List;

public interface ActivityLogService {
    void log(String action, String userEmail, String description, String entityName);
    List<ActivityLog> getAll();
}
