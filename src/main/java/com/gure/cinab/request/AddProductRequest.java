package com.gure.cinab.request;

import com.gure.cinab.model.Category;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Request DTO (Data Transfer Object) for adding a new product to the system.
 * This class encapsulates the product details provided when adding a new product.
 *
 * <p>It includes fields such as product name, brand, price, inventory, description, and category.</p>
 *
 * @since 1.0
 */
@Data
public class AddProductRequest {

    /**
     * The unique identifier of the product.
     * Note: Typically, this is generated automatically when the product is added to the system,
     * but it is included here for completeness and possible future extension.
     */
    private long id;

    /**
     * The name of the product.
     * This field is required for adding a product.
     */
    private String name;

    /**
     * The brand of the product.
     * This field is optional but can be used to filter or categorize products.
     */
    private String brand;

    /**
     * The price of the product.
     * This field is required and represents the price at which the product will be sold.
     */
    private BigDecimal price;

    /**
     * The inventory level for the product.
     * This field is required and specifies how many units of the product are available.
     */
    private int inventory;

    /**
     * A description of the product.
     * This field provides additional information about the product for the end-user.
     * It is optional.
     */
    private String description;

    /**
     * The category to which the product belongs.
     * This field is required for categorizing the product.
     */
    private Category category;
}
