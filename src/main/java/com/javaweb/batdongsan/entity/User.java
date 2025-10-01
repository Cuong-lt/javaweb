package com.javaweb.batdongsan.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    String userName;

    @NotBlank(message = "Password must not be blank")
    String password;

    @NotBlank(message = "Full name must not be blank")
    String fullName;

    @Column(length = 10)
    String phone;

    @Size(max = 500)
    String email;


    Integer status = 1;
    String avatar;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserRole> userRoles = new ArrayList<>();
}
