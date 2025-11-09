package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {
}
