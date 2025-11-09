package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.entity.ActivityLog;
import com.javaweb.batdongsan.repository.ActivityLogRepository;
import com.javaweb.batdongsan.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public void log(String action, String userEmail, String description, String entityName){
        ActivityLog log = new ActivityLog();
        log.setAction(action);
        log.setDescription(description);
        log.setUserEmail(userEmail);
        log.setEntityName(entityName);
        activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getAll() {
        List<ActivityLog> logs = activityLogRepository.findAll();
        return logs;
    }
}
