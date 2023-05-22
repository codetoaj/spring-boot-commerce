package com.amalan.test.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "email")
    public String email;

    @Column(name = "phonenumber")
    public String phonenumber;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    public String role;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    public Status status;

    @CreationTimestamp
    @Column(name = "createdat", updatable = false)
    public LocalDateTime createdat;

    @UpdateTimestamp
    @Column(name = "updatedat")
    public LocalDateTime updatedat;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
