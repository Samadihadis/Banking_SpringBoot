package com.samadihadis.Banking.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    private Long id;
    private String UserName;
    private String password;
    private String roles;

    public User() {
    }

}
