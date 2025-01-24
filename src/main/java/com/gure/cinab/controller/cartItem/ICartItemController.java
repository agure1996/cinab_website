package com.gure.cinab.controller.cartItem;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * The interface defining the contract for managing cart items in the application.
 * <p>
 * This interface contains methods to:
 * - Add items to a cart
 * - Remove items from a cart
 * - Update the quantity of items in a cart
 * </p>
 * Each method returns a {@link ResponseEntity<ApiResponse>} that encapsulates the operation's outcome
 * and includes appropriate HTTP status codes.
 *
 * @since 1.0.9
 */
public interface ICartItemController {

    /**
     * Adds an item to a specified cart.
     *
     * @param cartId   the unique ID of the cart to which the item will be added; must not be null.
     * @param itemId   the unique ID of the item to add; must not be null.
     * @param quantity the quantity of the item to add; must not be null or negative.
     * @return a {@link ResponseEntity<ApiResponse>} containing a success message or an error message.
     * @throws ResourceNotFoundException if the cart or item with the specified ID does not exist.
     */
    ResponseEntity<ApiResponse> addItemToCart(Long cartId, Long itemId, Integer quantity);

    /**
     * Removes an item from a specified cart.
     *
     * @param cartId the unique ID of the cart from which the item will be removed; must not be null.
     * @param itemId the unique ID of the item to remove; must not be null.
     * @return a {@link ResponseEntity<ApiResponse>} containing a success message or an error message.
     * @throws ResourceNotFoundException if the cart or item with the specified ID does not exist.
     */
    ResponseEntity<ApiResponse> removeItemFromCart(Long cartId, Long itemId);

    /**
     * Updates the quantity of an item in a specified cart.
     *
     * @param cartId   the unique ID of the cart containing the item; must not be null.
     * @param itemId   the unique ID of the item to update; must not be null.
     * @param quantity the new quantity of the item; must not be null or negative.
     * @return a {@link ResponseEntity<ApiResponse>} containing a success message or an error message.
     * @throws ResourceNotFoundException if the cart or item with the specified ID does not exist.
     */
    ResponseEntity<ApiResponse> updateItemQuantity(Long cartId, Long itemId, Integer quantity);
}
