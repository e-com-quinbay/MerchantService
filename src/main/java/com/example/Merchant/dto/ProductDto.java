package com.example.Merchant.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class ProductDto {

    public String id;
    public Integer merchantId;
    public String name;
    public HashMap<String, String> additionalDetails = new HashMap<>();
    public Double price;
    public Integer stock;
    public String category;
    public String image[];
}
