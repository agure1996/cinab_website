package com.gure.cinab.service.cart;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Cart;
import com.gure.cinab.model.CartItem;
import com.gure.cinab.model.Product;
import com.gure.cinab.repository.CartItemRepository;
import com.gure.cinab.repository.CartRepository;
import com.gure.cinab.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    // Repositories and services injected via constructor using Lombok @AllArgsConstructor
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final IProductService productService;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {

        /*
         * 1 - Fetch the cart using the provided cartId
         * 2 - Fetch the product using the provided productId
         * 3 - Check if the product already exists in the cart:
         *     a) If it exists, increase its quantity.
         *     b) If not, create a new CartItem and add it to the cart.
         */

        Cart cart = cartService.getCart(cartId); // Get the cart
        Product product = productService.getProductById(productId); // Get the product

        // Check if the product already exists in the cart
        CartItem cartItem = getCartItem(cartId, productId);

        if (cartItem.getId() == null) {
            // Create a new CartItem if it doesn't already exist
            cartItem.setCart(cart); // Associate the cart with the item
            cartItem.setProduct(product); // Associate the product with the item
            cartItem.setQuantity(quantity); // Set the initial quantity
            cartItem.setUnitPrice(product.getPrice()); // Set the price per unit
        } else {
            // If the item exists, increase its quantity
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        // Calculate the total price of the item (unitPrice * quantity)
        cartItem.setTotalPrice();

        // Add the item to the cart
        cart.addItem(cartItem);

        // Save the updated item and cart to the database
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

        // Fetch the cart by cartId
        Cart cart = cartService.getCart(cartId);

        // Find the item to remove; throw an exception if it doesn't exist
        CartItem itemToRemove = getCartItem(cartId, productId);

        // Remove the item from the cart
        cart.removeItem(itemToRemove);

        // Save the updated cart to the database
        cartRepository.save(cart);
    }

    @Override
    public void updateItemInCart(Long cartId, Long productId, int quantity) {

        // Fetch the cart by cartId
        Cart cart = cartService.getCart(cartId);

        // Find the cart item and update its quantity
        cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity); // Update the quantity
                    item.setUnitPrice(item.getProduct().getPrice()); // Update the price per unit
                    item.setTotalPrice(); // Recalculate the total price
                });

        // Update the cart's total amount
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);

        // Save the updated cart to the database
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {

        Cart cart = cartService.getCart(cartId); // Get the cart
        return cart
                .getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

    }
}
