package com.javaweb.batdongsan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class PasswordReset extends BaseEntity{
    @Column(unique = true, nullable = false)
    String otp;

    @Column(unique = true)
    String email;

    LocalDateTime expiryDate;
}
