package com.javaweb.batdongsan.model.request.project;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {
    Long id;
    String name;
    String description;
    String location; // Vị trí
    String investor;//Chủ đầu tư
    String status;
    LocalDate startDate; // Ngày bắt đầu dự án
    LocalDate endDate; // Ngày kết thúc dự án
    String userName;
}
