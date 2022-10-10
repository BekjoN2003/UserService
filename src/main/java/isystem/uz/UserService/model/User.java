package isystem.uz.UserService.model;

import isystem.uz.UserService.type.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String lastname;
    private String firstname;
    private String phone;
    private String email;
    private String password;
    private UserType userType;
    private String avatar; // https://isystem.uz/profile/1/image/1231asfd24fknaskd.png
    private LocalDate birth;
    private String address;
    private String address2;
    @Column(name = ("city_id"))
    private Integer cityId;
    private String postcode;
    @Column(name = ("qr_code"))
    private String qrCode;
    @Column(name = ("email_verified_at"))
    private LocalDateTime emailVerifiedAt;
    @Column(name = ("phone_verified_at"))
    private LocalDateTime phoneVerifiedAt;
    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
    private Boolean status;
}
