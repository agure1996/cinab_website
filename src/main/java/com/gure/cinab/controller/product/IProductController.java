package com.gure.cinab.controller.product;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.request.AddProductRequest;
import com.gure.cinab.request.ProductUpdateRequest;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * The interface defining the contract for handling product-related HTTP operations.
 * <p>
 * This interface contains methods that handle basic CRUD operations for products such as:
 * - Retrieving products by various criteria (ID, name, brand, category, etc.)
 * - Adding, updating, and deleting products
 * </p>
 * Each method returns a {@link ResponseEntity<ApiResponse>} which encapsulates the result
 * of the operation along with appropriate HTTP status codes.
 *
 * @since 1.0.8
 */
public interface IProductController {

    /**
     * Retrieve all products.
     *
     * @return a {@link ResponseEntity<ApiResponse>} containing a list of all products.
     */
    ResponseEntity<ApiResponse> getAllProducts();

    /**
     * Retrieve a product by its unique ID.
     *
     * @param productId the unique ID of the product to retrieve; must not be null.
     * @return a {@link ResponseEntity<ApiResponse>} containing the product details
     * or an error message if not found.
     * @throws ResourceNotFoundException if no product with the specified ID exists.
     */
    ResponseEntity<ApiResponse> getProductById(Long productId);

    /**
     * Add a new product to the system.
     *
     * @param newProduct the details of the product to add, provided in a {@link AddProductRequest} object; must not be null.
     * @return a {@link ResponseEntity<ApiResponse>} containing the success message and the added product.
     */
    ResponseEntity<ApiResponse> addProduct(AddProductRequest newProduct);

    /**
     * Update an existing product's information.
     *
     * @param id      the unique ID of the product to update; must not be null.
     * @param product the new details to update the product with, provided in a {@link ProductUpdateRequest} object; must not be null.
     * @return a {@link ResponseEntity<ApiResponse>} containing the success message and updated product details.
     * @throws ResourceNotFoundException if no product with the specified ID exists.
     */
    ResponseEntity<ApiResponse> updateProduct(Long id, ProductUpdateRequest product);

    /**
     * Delete a product by its unique ID.
     *
     * @param productId the unique ID of the product to delete; must not be null.
     * @return a {@link ResponseEntity<ApiResponse>} containing the success message or error message if the product is not found.
     * @throws ResourceNotFoundException if no product with the specified ID exists.
     */
    ResponseEntity<ApiResponse> deleteProduct(Long productId);

    /**
     * Retrieve a product by its brand name and category name.
     *
     * @param categoryName the category name; must not be null or empty.
     * @param brandName    the brand name of the product; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing the product details or error message if not found.
     * @throws ResourceNotFoundException if no product matching the brand and category is found.
     */
    ResponseEntity<ApiResponse> getProductByCategoryAndBrand(String categoryName, String brandName);

    /**
     * Retrieve a product by its brand name and product name.
     *
     * @param brandName   the brand name of the product; must not be null or empty.
     * @param productName the product name; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing the product details or error message if not found.
     * @throws ResourceNotFoundException if no product matching the brand and name is found.
     */
    ResponseEntity<ApiResponse> getProductByBrandAndName(String brandName, String productName);

    /**
     * Retrieve products that belong to a specific category.
     *
     * @param category the category name; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing the products that belong to the specified category.
     * @throws ResourceNotFoundException if no products are found in the specified category.
     */
    ResponseEntity<ApiResponse> getProductsByCategory(String category);

    /**
     * Retrieve a product by its name.
     *
     * @param name the name of the product; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing the product details or error message if not found.
     * @throws ResourceNotFoundException if no product with the specified name exists.
     */
    ResponseEntity<ApiResponse> getProductByName(String name);

    /**
     * Retrieve products by their brand name.
     *
     * @param brand the brand name; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing a list of products from the specified brand.
     * @throws ResourceNotFoundException if no products from the specified brand exist.
     */
    ResponseEntity<ApiResponse> getProductByBrand(String brand);

    /**
     * Count the number of products that match a specific brand and name.
     *
     * @param brand the brand name; must not be null or empty.
     * @param name  the product name; must not be null or empty.
     * @return a {@link ResponseEntity<ApiResponse>} containing the count of matching products.
     * @throws ResourceNotFoundException if no products matching the specified brand and name exist.
     */
    ResponseEntity<ApiResponse> countProductsByBrandAndName(String brand, String name);
}
