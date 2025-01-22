package com.gure.cinab.service.cart;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.CartItem;

/**
 * Interface defining operations for managing cart items in a shopping cart system.
 * Provides methods to add, remove, and update items in a cart.
 */
public interface ICartItemService {

    /**
     * Adds an item to a specified cart.
     * If the item already exists, its quantity is incremented by the specified amount.
     *
     * @param cartId    the unique identifier of the cart.
     * @param productId the unique identifier of the product to be added.
     * @param quantity  the quantity of the product to add. Must be greater than zero.
     */
    void addItemToCart(Long cartId, Long productId, int quantity);

    /**
     * Removes a specific item from the cart.
     * If the item does not exist, the method does nothing.
     *
     * @param cartId    the unique identifier of the cart.
     * @param productId the unique identifier of the product to be removed.
     */
    void removeItemFromCart(Long cartId, Long productId);

    /**
     * Updates the quantity of a specific item in the cart.
     * If the new quantity is zero or less, the item is removed from the cart.
     *
     * @param cartId    the unique identifier of the cart.
     * @param productId the unique identifier of the product to update.
     * @param quantity  the new quantity of the product. Must be zero or greater.
     */
    void updateItemInCart(Long cartId, Long productId, int quantity);

    /**
     * Retrieves a specific item from a cart based on the cart ID and product ID.
     * Method was created to prevent repetitive code actions for getting Cart-Items.
     *
     * @param cartId    the unique identifier of the cart.
     * @param productId the unique identifier of the product to retrieve from the cart.
     * @return the {@link CartItem} associated with the specified cart and product.
     * @throws ResourceNotFoundException if the product is not found in the cart.
     */
    CartItem getCartItem(Long cartId, Long productId);
}
