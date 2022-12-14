package com.example.Merchant.repository;


import com.example.Merchant.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Integer> {
    List<Merchant> findByEmail(String email);
    List<Merchant> findByMobile(Long mobile);
}
