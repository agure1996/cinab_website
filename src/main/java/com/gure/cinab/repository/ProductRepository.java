package com.gure.cinab.repository;

import com.gure.cinab.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Product entities.
 * <p>
 * This interface extends the {@link JpaRepository} interface, providing basic CRUD operations
 * and custom queries related to products.
 * </p>
 *
 * @since 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products by the specified category name.
     *
     * @param category the category name to filter products by
     * @return a list of products belonging to the specified category
     */
    List<Product> findByCategoryName(String category);

    /**
     * Finds all products by the specified brand name.
     *
     * @param brand the brand name to filter products by
     * @return a list of products belonging to the specified brand
     */
    List<Product> findByBrand(String brand);


    /**
     * Finds all products by the specified category and brand.
     *
     * @param category the category name to filter products by
     * @param brand    the brand name to filter products by
     * @return a list of products belonging to the specified category and brand
     */
    List<Product> findByCategoryNameAndBrand(String category, String brand);

    /**
     * Finds all products by the specified product name.
     *
     * @param name the name of the product to search for
     * @return a list of products with the specified name
     */
    List<Product> findByName(String name);

    /**
     * Finds all products by the specified brand and product name.
     *
     * @param brand the brand of the products to search for
     * @param name  the name of the products to search for
     * @return a list of products matching the specified brand and name
     */
    List<Product> findByBrandAndName(String brand, String name);

    /**
     * Counts the number of products matching the specified brand and name.
     *
     * @param brand the brand of the products
     * @param name  the name of the products
     * @return the number of products matching the specified brand and name
     */
    Long countByBrandAndName(String brand, String name);
}
