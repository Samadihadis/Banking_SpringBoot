package com.samadihadis.Banking.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String roles;

    public User() {
    }

    public User(Long id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
