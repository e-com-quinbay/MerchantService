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

@Service
public class MerchantService {

    @Autowired
    MerchantRepository merchantRepository;


    public Merchant login(String email, String password)
    {
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


    public Merchant signup(Merchant merchant)
    {

        List<Merchant> checkRegister=merchantRepository.findByEmail(merchant.getEmail());
        if(checkRegister.size()==0) {
            String password = merchant.getPassword();
            String hashPassword = password; //hash password

            merchant.setPassword(hashPassword);

            Merchant newMerchant = merchantRepository.save(merchant);
            return  newMerchant;
        }
        return  null;
    }



}
