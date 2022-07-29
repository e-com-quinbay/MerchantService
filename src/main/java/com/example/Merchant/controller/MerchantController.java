package com.example.Merchant.controller;


import com.example.Merchant.Urls.Urls;
import com.example.Merchant.dto.ProductDto;
import com.example.Merchant.entity.Merchant;
import com.example.Merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/merchant")
@CrossOrigin(value = "*")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping(value="/login")
    public Merchant login(@RequestBody Merchant merchant)
    {
        String email = merchant.getEmail();
        String password = merchant.getPassword();
        return merchantService.login(email,password);
    }

    @PostMapping(value="/signup")
    public Merchant register(@RequestBody Merchant merchant)
    {
        return merchantService.signup(merchant);
    }


    @PostMapping(value="/create")
    public ProductDto createProduct(@RequestBody ProductDto product)
    {
        String url=Urls.addProduct;
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.postForObject(url,product,ProductDto.class);

//        restTemplate.get
    }
    @GetMapping(value = "/get/{id}")
    public ProductDto findById(@PathVariable("id") Integer id)
    {
        String url=Urls.readProduct;
        RestTemplate restTemplate=new RestTemplate();

        return restTemplate.getForObject(url,ProductDto.class);
//        restTemplate.get
    }

    @DeleteMapping(value = "/delete/{id}")
    public void  delete(@PathVariable("id") Integer id)
    {
        String url=Urls.delProduct;
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getForObject(url,Void.class);
    }

    @PutMapping(value="/update/")
    public ProductDto update(@RequestBody ProductDto product)
    {
        String url=Urls.updateProduct;
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.postForObject(url,product,ProductDto.class);
    }

}
