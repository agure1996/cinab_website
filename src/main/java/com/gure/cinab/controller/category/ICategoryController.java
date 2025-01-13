/**
 * The ICategoryController interface defines the contract for handling
 * category-related operations in the application. It provides endpoints for
 * creating, retrieving, updating, and deleting categories.
 */
package com.gure.cinab.controller.category;

import com.gure.cinab.model.Category;
import com.gure.cinab.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICategoryController {

    /**
     * Retrieves all available categories.
     *
     * @return ResponseEntity containing an ApiResponse with a list of categories.
     * @since 1.0.7
     */
    ResponseEntity<ApiResponse> getAllCategories();

    /**
     * Adds a new category to the system.
     *
     * @param name the Category object containing details of the category to be added.
     * @return ResponseEntity containing an ApiResponse with details of the added category.
     */
    ResponseEntity<ApiResponse> addCategory(@RequestBody Category name);

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param id the unique ID of the category to retrieve.
     * @return ResponseEntity containing an ApiResponse with the category details.
     */
    ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id);

    /**
     * Retrieves a category by its name.
     *
     * @param name the name of the category to retrieve.
     * @return ResponseEntity containing an ApiResponse with the category details.
     */
    ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name);

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique ID of the category to delete.
     * @return ResponseEntity containing an ApiResponse indicating the result of the operation.
     */
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id);

    /**
     * Updates the details of an existing category.
     *
     * @param id the unique ID of the category to update.
     * @param category the updated Category object with new details.
     * @return ResponseEntity containing an ApiResponse indicating the result of the operation.
     */
    ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category);
}
