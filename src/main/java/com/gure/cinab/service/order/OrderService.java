package com.gure.cinab.service.order;

import com.gure.cinab.dto.OrderDTO;
import com.gure.cinab.enums.OrderStatus;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Cart;
import com.gure.cinab.model.Order;
import com.gure.cinab.model.OrderItem;
import com.gure.cinab.model.Product;
import com.gure.cinab.repository.OrderRepository;
import com.gure.cinab.repository.ProductRepository;
import com.gure.cinab.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ICartService cartService;



    //helper methods below are used for placing order
    @Transactional
    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCart(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order saveOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return saveOrder;
    }


    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                    productRepository.save(product);

                    return new OrderItem(
                            order,
                            product,
                            cartItem.getQuantity(),
                            cartItem.getUnitPrice());
                }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {

        return orderItemList.stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<OrderDTO> getUserOrders(Long userId){
        List<Order> orders = orderRepository.findByUserId(userId);
//        return  orders.stream().map(this :: convertToDTO).toList();
        return null;
    }
//
//    private Object convertToDTO(Order order) {
//        return modelMapper.map(order, OrderDTO.class);
//    }

}
