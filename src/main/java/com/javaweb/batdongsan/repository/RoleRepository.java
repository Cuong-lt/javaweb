package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByCode(String code);
    Optional<Role> findByCode(String code);
}
