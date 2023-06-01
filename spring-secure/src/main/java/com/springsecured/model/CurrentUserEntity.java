package com.springsecured.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CurrentUserEntity {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
}
