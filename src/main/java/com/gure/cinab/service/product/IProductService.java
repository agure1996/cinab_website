package com.gure.cinab.service.product;

import com.gure.cinab.dto.ProductDTO;
import com.gure.cinab.model.Product;
import com.gure.cinab.request.AddProductRequest;
import com.gure.cinab.request.ProductUpdateRequest;

import java.util.List;

/**
 * Interface for managing products in the system.
 *
 * @since 1.0
 */
public interface IProductService {

    /**
     * Adds a new product.
     *
     * @param request the DTO containing details of the new product to be added
     * @return the added product entity
     */
    Product addProduct(AddProductRequest request);

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID
     */
    Product getProductById(Long id);

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void deleteProduct(Long id);

    /**
     * Updates a product by its ID.
     *
     * @param product the product update details (including new values for product attributes)
     * @param id      the ID of the product to update
     * @return the updated product entity
     */

    Product updateProduct(ProductUpdateRequest product, Long id);

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves products by their category.
     *
     * @param category the category name (or identifier) for filtering the products
     * @return a list of products that belong to the specified category
     */
    List<Product> getProductsByCategory(String category);

    /**
     * Retrieves products by their brand.
     *
     * @param brand the brand of the products
     * @return a list of products of the specified brand
     */
    List<Product> getProductsByBrand(String brand);

    /**
     * Retrieves products by their category and brand.
     *
     * @param category the category of the products
     * @param brand    the brand of the products
     * @return a list of products matching the specified category and brand
     */
    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    /**
     * Retrieves products by their name.
     *
     * @param name the name of the products
     * @return a list of products matching the specified name
     */
    List<Product> getProductsByName(String name);

    /**
     * Retrieves products by their brand and name.
     *
     * @param brand the brand of the products
     * @param name  the name of the products
     * @return a list of products matching the specified brand and name
     */
    List<Product> getProductsByBrandAndName(String brand, String name);

    /**
     * Counts the number of products by their brand and name.
     *
     * @param brand the brand of the products
     * @param name  the name of the products
     * @return the count of products matching the specified brand and name
     */
    Long countProductsByBrandAndName(String brand, String name);


    /**
     * Converts a product and maps to a DTO format .
     *
     * @param product the product to be converted
     * @return the converted product as a DTO
     */
    ProductDTO convertToDTO(Product product);

    /**
     * retrieves a list of converted products to DTO .
     *
     * @param products the products as a list to be converted
     * @return streams a list of converted products
     */
    List<ProductDTO> getConvertedProducts(List<Product> products);
}
