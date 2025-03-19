package com.gure.cinab.repository;

import com.gure.cinab.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Retrieves an order by user id.
     *
     * @param userId the ID of the user related to the order we want to retrieve
     * @return Order found by user Id
     */
    Cart findByUserId(Long userId);
}
