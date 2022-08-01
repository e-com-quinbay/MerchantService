package com.example.Merchant.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private long mobile;
}
