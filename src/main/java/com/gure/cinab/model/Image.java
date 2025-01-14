package com.gure.cinab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

/**
 * Represents an image associated with a product in the system.
 * <p>
 * This entity stores image details including the image's filename, type, the actual image data,
 * and the download URL for the image. It is related to a specific product.
 * </p>
 *
 * @since 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    /**
     * The unique identifier for the image.
     * <p>
     * This ID is automatically generated and managed by the database.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The name of the image file.
     * <p>
     * This is the name of the image file, typically used for identifying or displaying the image.
     * </p>
     */
    private String fileName;

    /**
     * The type of the image file (e.g., "jpg", "png").
     * <p>
     * This indicates the format or type of the image (e.g., JPEG, PNG).
     * </p>
     */
    private String fileType;

    /**
     * The actual image data.
     * <p>
     * Stored as a large object (LOB) in the database, it contains the binary data of the image.
     * </p>
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob image;

    /**
     * The download URL for the image.
     * <p>
     * This is the URL where the image can be accessed for download.
     * </p>
     */
    private String downloadUrl;

    /**
     * The product that this image is associated with.
     * <p>
     * This is a many-to-one relationship with the {@link Product} entity, where each image is linked
     * to a specific product.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
