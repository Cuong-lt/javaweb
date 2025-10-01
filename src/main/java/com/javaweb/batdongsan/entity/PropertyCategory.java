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
public class PropertyCategory extends BaseEntity {
    /**
     * Loại bất động sản gồm Nhà, đất, Căn hộ,.. Có thể thêm vào
     */
    @Column(unique = true, nullable = false)
    String name;
}
