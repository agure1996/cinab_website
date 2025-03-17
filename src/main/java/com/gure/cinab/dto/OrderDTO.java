package com.gure.cinab.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Original Order class has a bunch of objects that reference other tables,
 * since we want to make requests without calling the entire order object we use DTOs
 * This is DTO of the Order class
 */
@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDTO> items;
}
