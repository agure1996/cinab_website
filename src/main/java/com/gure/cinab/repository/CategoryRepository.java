package com.gure.cinab.repository;

import com.gure.cinab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Category} entities.
 * Provides data access methods for performing CRUD operations on {@link Category} objects.
 * Extends {@link JpaRepository} to leverage built-in JPA operations.
 *
 * @since 1.0.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Retrieves a {@link Category} by its name.
     *
     * @param name the name of the category to search for
     * @return the {@link Category} entity matching the given name, or {@code null} if no such category exists
     */
    Category findByName(String name);

    boolean existsByName(String name);
}
