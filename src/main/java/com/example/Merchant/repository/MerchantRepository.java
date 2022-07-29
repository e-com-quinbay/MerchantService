package com.example.Merchant.repository;


import com.example.Merchant.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface MerchantRepository extends CrudRepository<Merchant,String> {
    List<Merchant> findByEmail(String email);
}
