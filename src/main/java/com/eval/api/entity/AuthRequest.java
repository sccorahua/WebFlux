package com.eval.api.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
