package com.gure.cinab.service.cart;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Cart;
import com.gure.cinab.repository.CartItemRepository;
import com.gure.cinab.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAll();
        cart.getCartItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
//        return cart.getCartItems()
//                .stream()
//                .map(CartItem::getTotalPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        return cartRepository.save(newCart).getId();
    }

}
