package com.gure.cinab.controller.cart;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * Interface for managing cart-related operations.
 * This interface defines the contract for actions such as retrieving cart details,
 * clearing a cart, and calculating the total price of items in a cart.
 */
public interface ICartController {

    /**
     * Retrieves the details of a cart by its ID.
     *
     * @param cartId the ID of the cart to retrieve; must not be null
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with the cart details,
     *         or an appropriate error message if the cart is not found
     * @throws IllegalArgumentException if the provided cartId is invalid
     *@throws ResourceNotFoundException if the cart with the specified ID is not found.
     */
    ResponseEntity<ApiResponse> getCart(Long cartId);

    /**
     * Clears all items in the specified cart.
     *
     * @param cartId the ID of the cart to clear; must not be null
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with a success message,
     *         or an appropriate error message if the cart is not found
     * @throws IllegalArgumentException if the provided cartId is invalid
     * @throws ResourceNotFoundException if the cart with the specified ID is not found.
     */
    ResponseEntity<ApiResponse> clearCart(Long cartId);

    /**
     * Calculates and retrieves the total price of items in the specified cart.
     *
     * @param cartId the ID of the cart for which to calculate the total price; must not be null
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with the total price
     *         of items in the cart, or an appropriate error message if the cart is not found
     * @throws IllegalArgumentException if the provided cartId is invalid
     * @throws ResourceNotFoundException if the cart with the specified ID is not found.
     */
    ResponseEntity<ApiResponse> getTotalAmount(Long cartId);
}
