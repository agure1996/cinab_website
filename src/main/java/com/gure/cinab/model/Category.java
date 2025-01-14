package com.gure.cinab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a product category in the system.
 * <p>
 * This entity is used to categorize products. It contains the category's name
 * and the list of products associated with it.
 * </p>
 *
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    /**
     * The unique identifier for the category.
     * <p>
     * This ID is automatically generated and managed by the database.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The name of the category.
     * <p>
     * This represents the category's name, such as "Electronics", "Furniture", etc.
     * </p>
     */
    private String name;

    /**
     * The list of products associated with this category.
     * <p>
     * This is a bidirectional relationship with the {@link Product} entity,
     * where each product has a reference to a category.
     * </p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    /**
     * Constructor to create a category with a specific name.
     * <p>
     * Used when a new category is created, typically from a request or input.
     * </p>
     *
     * @param name the name of the category to create
     */
    public Category(String name) {
        this.name = name;
    }
}
