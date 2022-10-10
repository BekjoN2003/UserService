package com.example.userservice.model;

import com.example.userservice.type.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "userr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UserType userType;
    @Column(unique = true)
    private String phone;
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    @Column(nullable = false)
    private LocalDate birth;
    private String address;
    private String address2;
    private Integer cityId;
    private String postCode;
    private String qrCode;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime phoneVerifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean status;


}
