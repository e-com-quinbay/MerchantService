package com.example.Merchant.controller;


import com.example.Merchant.Urls.Urls;
import com.example.Merchant.dto.MerchantDto;
import com.example.Merchant.dto.ProductDto;
import com.example.Merchant.entity.Merchant;
import com.example.Merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value="/merchant")
@CrossOrigin(value = "*")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping(value="/login")
    public MerchantDto login(@RequestBody MerchantDto merchantDto)
    {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
        String email = merchant.getEmail();
        String password = merchant.getPassword();
        merchant= merchantService.login(email,password);
        MerchantDto returnDto = new MerchantDto();
        BeanUtils.copyProperties(merchant,returnDto);
        return  returnDto;

    }

    @PostMapping(value="/signup")
    public MerchantDto register(@RequestBody MerchantDto merchantDto)
    {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
        merchant = merchantService.signup(merchant);
        MerchantDto returnDto = new MerchantDto();
        BeanUtils.copyProperties(merchant,returnDto);
        return  returnDto;
    }


    @PostMapping(value="/create")
    public ProductDto createProduct(@RequestBody ProductDto product)
    {
        return merchantService.createProduct(product);
    }


    @GetMapping(value = "/get/product/{id}")
    public ProductDto findProductById(@PathVariable("id") String id)
    {
        return merchantService.findProductById(id);
    }


    @GetMapping(value = "/get/{id}")
    public List<ProductDto> findByMerchantId(@PathVariable("id") Integer id)
    {
        return merchantService.findByMerchantId(id);
    }

    @DeleteMapping(value = "/delete/product/{id}")
    public void  deleteProduct(@PathVariable("id") String id)
    {
       merchantService.deleteProduct(id);
    }

    @PostMapping(value="/update/product")
    public ProductDto updateProduct(@RequestBody ProductDto product)
    {
        return merchantService.updateProduct(product);
    }

//    @PutMapping(value = "/update")



}
