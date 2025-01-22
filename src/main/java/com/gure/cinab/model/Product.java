package com.gure.cinab.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a product in the system.
 * <p>
 * The Product class contains details about the product such as its name, brand, price, inventory, description,
 * associated category, and images.
 * </p>
 * <p>
 * The class is annotated with JPA annotations for persistence to a relational database, and Lombok annotations
 * are used to generate getter, setter, and constructors.
 * </p>
 *
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    /**
     * The unique identifier of the product.
     * This field is automatically generated when a new product is persisted.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the product.
     * This field is required and represents the name displayed to the users.
     */
    private String name;

    /**
     * The brand of the product.
     * This field is optional and is used to specify the manufacturer or brand of the product.
     */
    private String brand;

    /**
     * The price of the product.
     * This field represents the price at which the product is sold.
     * It is represented as a BigDecimal to handle precision for financial calculations.
     */
    private BigDecimal price;

    /**
     * The inventory count for the product.
     * This field specifies the number of units available in stock.
     */
    private int inventory;

    /**
     * A short description of the product.
     * This field can provide additional details about the product, such as features or specifications.
     */
    private String description;

    /**
     * The category to which the product belongs.
     * This is a many-to-one relationship, where many products can belong to one category.
     * The product's category is defined by the category associated with it.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * The list of images associated with the product.
     * This is a one-to-many relationship, where one product can have multiple images.
     * The images are stored in a separate Image entity and are linked to the product.
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
    private List<Image> images;

    /**
     * Constructs a new Product with the specified details.
     *
     * @param name        the name of the product
     * @param brand       the brand of the product
     * @param price       the price of the product
     * @param inventory   the inventory count for the product
     * @param description a description of the product
     * @param category    the category of the product
     */
    public Product(String name, String brand, BigDecimal price, int inventory, String description, Category category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
    }
}
