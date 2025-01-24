package com.gure.cinab.controller.category;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Category;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;

/**
 * The ICategoryController interface defines the contract for handling
 * category-related operations in the application. It provides methods for
 * creating, retrieving, updating, and deleting categories.
 */
public interface ICategoryController {

    /**
     * Retrieves all available categories.
     *
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with a list of categories.
     * @since 1.0.7
     */
    ResponseEntity<ApiResponse> getAllCategories();

    /**
     * Adds a new category to the system.
     *
     * @param name the {@link Category} object containing details of the category to be added.
     *             The category name must not be null or empty.
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with details of the added category.
     * @throws IllegalArgumentException if the provided category details are invalid.
     */
    ResponseEntity<ApiResponse> addCategory(Category name);

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param id the unique ID of the category to retrieve. Must not be null.
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with the category details.
     * @throws IllegalArgumentException if the provided ID is invalid.
     * @throws ResourceNotFoundException if no category with the specified ID exists.
     */
    ResponseEntity<ApiResponse> getCategoryById(Long id);

    /**
     * Retrieves a category by its name.
     *
     * @param name the name of the category to retrieve. Must not be null or empty.
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} with the category details.
     * @throws IllegalArgumentException if the provided name is invalid.
     * @throws ResourceNotFoundException if no category with the specified name exists.
     */
    ResponseEntity<ApiResponse> getCategoryByName(String name);

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique ID of the category to delete. Must not be null.
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} indicating the result of the operation.
     * @throws IllegalArgumentException if the provided ID is invalid.
     * @throws ResourceNotFoundException if no category with the specified ID exists.
     */
    ResponseEntity<ApiResponse> deleteCategory(Long id);

    /**
     * Updates the details of an existing category.
     *
     * @param id the unique ID of the category to update. Must not be null.
     * @param category the updated {@link Category} object containing new details for the category.
     *                 The category name must not be null or empty.
     * @return a {@link ResponseEntity} containing an {@link ApiResponse} indicating the result of the operation.
     * @throws ResourceNotFoundException if no category with the specified ID exists.
     * @throws IllegalArgumentException if the provided ID or category details are invalid.
     */
    ResponseEntity<ApiResponse> updateCategory(Long id, Category category);
}
