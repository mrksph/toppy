package com.mrksph.toppy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class UserEntity {
    @Id
    private String username;
    private String email;
    private String password;
    private String hash;
    private String salt;

}
