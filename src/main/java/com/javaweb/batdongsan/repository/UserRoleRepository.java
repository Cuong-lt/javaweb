package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUser_UserName(String userName);
    List<UserRole> findByRole_Code(String code);
}
