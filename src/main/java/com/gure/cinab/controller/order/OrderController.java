package com.gure.cinab.controller.order;

import com.gure.cinab.dto.OrderDTO;
import com.gure.cinab.model.Order;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/orders")
@EnableMethodSecurity()
public class OrderController implements IOrderController {

    private final IOrderService orderService;

    @PostMapping("/order")
    @Override
    @PreAuthorize("hasRole('ADMIN_ROLE') or hasRole('CUSTOMER_ROLE')")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
        try {
            Order order = orderService.placeOrder(userId);
            //convert the order to a orderDTO
            OrderDTO orderDTO = orderService.convertOrderToDTO(order);
            return ResponseEntity.ok(new ApiResponse("Item Ordered Successfully!", orderDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error Occurred!", e.getMessage()));
        }
    }

    @GetMapping("/{orderId}/order")
    @Override
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDTO order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse("Order Found!", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Oops!", e.getMessage()));
        }
    }

    @GetMapping("/{userId}/orders")
    @Override
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
        try {
            List<OrderDTO> userOrders = orderService.getUserOrders(userId);
            return ResponseEntity.ok(new ApiResponse("Order Found!", userOrders));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Oops!", e.getMessage()));
        }
    }
}

