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
public class PropertyStatus extends BaseEntity {
    /**
     * Trạng thái bất động sản: đang bán/ đã bán
     */
    @Column(unique = true, nullable = false)
    String name;
}
