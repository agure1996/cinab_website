package com.gure.cinab.repository;

import com.gure.cinab.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    void deleteAllByCartId(Long id);
}
