package com.javaweb.batdongsan.entity;

import com.javaweb.batdongsan.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project extends BaseEntity {

    String name;
    String description;
    String location; // Vị trí
    String investor;//Chủ đầu tư
    @Enumerated(EnumType.STRING)
    ProjectStatus status;

    LocalDate startDate; // Ngày bắt đầu dự án
    LocalDate endDate; // Ngày kết thúc dự án

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
}
