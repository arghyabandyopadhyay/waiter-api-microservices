package com.businessgenie.users.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JavaType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", updatable = false, length = 255, nullable = false)
    private UUID id;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;

    @Column(name = "EmailId", length = 255)
    private String emailId;

    @Column(name = "MobileNumber", length = 255)
    private String mobileNumber;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    @Column(name = "ProfileUrl", length = 255)
    private String profileUrl;

    @Column(name = "DeviceToken", length = 255)
    private String deviceToken;

    @Column(name = "JwtToken", length = 255)
    private String jwtToken;

    @Column(name = "LastLogin", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Column(name = "RoleId", nullable = false)
    private int roleId = 1;

    @Column(name = "Password", length = 255)
    private String password;

    @Column(name = "MasterSearchFilter", length = 255)
    private String masterSearchFilter;
}