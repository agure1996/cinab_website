package com.gure.cinab.service.cart;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Cart;

import java.math.BigDecimal;

/**
 * Provides an interface for managing shopping cart operations.
 * This service defines the core actions related to cart lifecycle management,
 * including retrieval, initialization, and operations for clearing or calculating totals.
 *
 * @since 1.0.9
 */
public interface ICartService {

    /**
     * Retrieves a cart by its unique identifier.
     *
     * @param id the unique identifier of the cart to retrieve
     * @return the {@link Cart} corresponding to the provided ID
     * @throws ResourceNotFoundException if the cart with the specified ID is not found
     */
    Cart getCart(Long id);

    /**
     * Clears the cart identified by the provided ID.
     * Removes all items in the cart and deletes the cart from the system.
     *
     * @param id the unique identifier of the cart to clear
     * @throws ResourceNotFoundException if the cart with the specified ID is not found
     */
    void clearCart(Long id);

    /**
     * Calculates the total price of all items in the cart.
     *
     * @param id the unique identifier of the cart whose total price is to be calculated
     * @return the total price as a {@link BigDecimal}
     * @throws ResourceNotFoundException if the cart with the specified ID is not found
     */
    BigDecimal getTotalPrice(Long id);

    /**
     * Initializes a new shopping cart and assigns it a unique identifier.
     *
     * @return the unique identifier of the newly created cart
     */
    Long initializeNewCart();
}
