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
public class PropertyType extends BaseEntity {
    /**
     * Hình thức giao dịch: Bán,Cho thuê
     */
    @Column(unique = true, nullable = false)
    String name;
}
