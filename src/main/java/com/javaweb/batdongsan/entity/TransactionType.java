package com.javaweb.batdongsan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionType extends BaseEntity {
    /**
     * Loại giao dịch gồm: mua/thuê
     */
    @Column(unique = true, nullable = false)
    String name;
}
