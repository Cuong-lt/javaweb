package com.javaweb.batdongsan.repository;

import com.javaweb.batdongsan.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset,Long> {
    Optional<PasswordReset> findByEmailAndOtp(String email, String otp);
    void deleteByEmail(String email);
}
