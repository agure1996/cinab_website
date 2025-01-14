package com.gure.cinab.repository;

import com.gure.cinab.model.Category;
import com.gure.cinab.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Image} entities.
 * Provides data access methods for performing CRUD operations on {@link Image} objects.
 * Extends {@link JpaRepository} to leverage built-in JPA operations.
 *
 * @since 1.0.4
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProductId(long id);
}
