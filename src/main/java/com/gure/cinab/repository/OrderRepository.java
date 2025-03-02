package com.gure.cinab.repository;
import com.gure.cinab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Order} entities.
 * Provides data access methods for performing CRUD operations on {@link Order} objects.
 * Extends {@link JpaRepository} to leverage built-in JPA operations.
 *
 * @since 1.0.9
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
