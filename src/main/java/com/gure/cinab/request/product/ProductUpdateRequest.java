package com.gure.cinab.request.product;

import com.gure.cinab.model.Category;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) used for updating a product in the system.
 * Contains the necessary fields to update a product's details.
 *
 * @since 1.0
 */
@Data
public class ProductUpdateRequest {
    private long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}

