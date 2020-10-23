package com.rinkesh.schoolmanagementapi.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author RinkeshKM
 * @Date 27/08/20
 */

@Entity(name = "users")
//@Table(name = "user")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String token;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "status")
    private String status;
}
