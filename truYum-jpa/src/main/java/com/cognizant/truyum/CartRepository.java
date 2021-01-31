package com.cognizant.truyum;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.truyum.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Map<Integer, Integer>> {

}
