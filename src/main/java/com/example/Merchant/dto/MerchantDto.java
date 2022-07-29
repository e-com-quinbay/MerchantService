package com.example.Merchant.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MerchantDto {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private long mobile;

}
