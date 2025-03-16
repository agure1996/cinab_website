package com.gure.cinab.controller.order;

import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;


public interface IOrderController {
    ResponseEntity<ApiResponse> createOrder(Long userId);

    ResponseEntity<ApiResponse> getOrderById(Long userId);

    ResponseEntity<ApiResponse> getUserOrders(Long userId);
}
