package com.gure.cinab.service.order;

import com.gure.cinab.dto.OrderDTO;
import com.gure.cinab.model.Order;

import java.util.List;

/**
 * Interface defining operations related to order management.
 */
public interface IOrderService {

    /**
     * Places a new order for a given user.
     *
     * @param userId the ID of the user placing the order
     * @return the created Order object
     */
    Order placeOrder(Long userId);
    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return the Order object if found
     */
    Order getOrder(Long orderId);
    /**
     * Retrieves an order by user id.
     *
     * @param userId the ID of the user related to the order we want to retrieve
     * @return list of Orders if found
     */
    List<OrderDTO> getUserOrders(Long userId);
}
