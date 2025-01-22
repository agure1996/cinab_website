package com.gure.cinab.service.cart;

import com.gure.cinab.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);


}
