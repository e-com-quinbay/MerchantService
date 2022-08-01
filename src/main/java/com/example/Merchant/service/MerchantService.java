package com.example.Merchant.service;

import com.example.Merchant.Urls.Urls;
import com.example.Merchant.dto.ProductDto;
import com.example.Merchant.entity.Merchant;
import com.example.Merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

    @Autowired
    MerchantRepository merchantRepository;


    public Merchant login(String email, String password) {
        List<Merchant> getUser = merchantRepository.findByEmail(email);

        if (getUser.size() > 0) {
            Merchant user = getUser.get(0);
            String storedPassword = user.getPassword();

            String hashedPassword = password; //hash the password field
            if (storedPassword.compareTo(hashedPassword) == 0)
                return user;
        }
        return null;

    }

    public Merchant signup(Merchant merchant) {

        List<Merchant> checkRegister = merchantRepository.findByEmail(merchant.getEmail());
        List<Merchant> checkMobile = merchantRepository.findByMobile(merchant.getMobile());
        if (checkRegister.size() == 0 && checkMobile.size() == 0) {
            String password = merchant.getPassword();
            String hashPassword = password; //hash password

            merchant.setPassword(hashPassword);

            Merchant newMerchant = merchantRepository.save(merchant);
            return newMerchant;
        }
        return null;
    }

    public ProductDto createProduct(ProductDto product) {

        if(product.stock>0&&product.price>0&&product.merchantId!=null) {
            String url = Urls.addProduct;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, product, ProductDto.class);
        }
        return null;
    }


    public List<ProductDto> findProductById(Integer id) {
        String url = Urls.readProduct;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url + id, List.class);
    }

    public String findMerchantName(Integer id)
    {
        Optional<Merchant> merchant=merchantRepository.findById(id);
        Merchant merchant1=null;
        if(merchant.isPresent())
        merchant1 = merchant.get();
        return merchant1.getName();
    }


    public void delete(String id) {

        String url = Urls.delProduct;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url+id,Void.class);
    }

    public ProductDto update(ProductDto product)
    {
        if(product.stock>0&&product.price>0&&product.merchantId!=null) {
            String url = Urls.updateProduct;
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForObject(url, product, ProductDto.class);
        }
        return null;
    }
}