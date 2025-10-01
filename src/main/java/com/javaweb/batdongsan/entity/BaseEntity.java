package com.javaweb.batdongsan.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreatedDate
    @Column(name = "createddate")
    LocalDateTime createdDate;

    @CreatedBy
    @Column(name = "createdby")
    String createdBy;

    @LastModifiedDate
    @Column(name = "modifieddate")
    LocalDateTime modifiedDate;

    @LastModifiedBy
    @Column(name = "modifiedby")
    String modifiedBy;

//    @Builder.Default
//    byte isActive = 1;
}
