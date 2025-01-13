package com.gure.cinab.service.category;

import com.gure.cinab.model.Category;
import java.util.List;

/**
 * Service interface for managing categories.
 * Provides methods to perform CRUD operations on {@link Category} objects.
 */
public interface ICategoryService {

    /**
     * Retrieves a {@link Category} by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return the {@link Category} object if found, or {@code null} if no category exists with the specified id
     */
    Category getCategoryById(Long id);

    /**
     * Retrieves a {@link Category} by its name.
     *
     * @param name the name of the category
     * @return the {@link Category} object if found, or {@code null} if no category exists with the specified name
     */
    Category getCategoryByName(String name);

    /**
     * Adds a new {@link Category} to the system.
     *
     * @param category the {@link Category} object to be added
     * @return the saved {@link Category} object after persistence
     */
    Category addCategory(Category category);

    /**
     * Updates an existing {@link Category} in the system.
     *
     * @param category the {@link Category} object with updated details
     * @param id the unique identifier of the {@link Category} object we want to update
     * @return the updated {@link Category} object after persistence
     * @throws IllegalArgumentException if the category does not exist
     */
    Category updateCategory(Category category, Long id);

    /**
     * Deletes a {@link Category} by its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     * @throws IllegalArgumentException if no category exists with the specified id
     */
    void deleteCategory(Long id);

    /**
     * Retrieves all {@link Category} objects from the system.
     *
     * @return a list of all categories
     */
    List<Category> getAllCategories();
}
