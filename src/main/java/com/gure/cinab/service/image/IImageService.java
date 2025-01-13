package com.gure.cinab.service.image;

import com.gure.cinab.dto.ImageDTO;
import com.gure.cinab.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service interface for managing images.
 * Provides methods to handle CRUD operations for image entities.
 *
 * @since 1.0.4
 */
public interface IImageService {

    /**
     * Retrieves an image by its unique identifier.
     *
     * @param id the unique identifier of the image
     * @return the {@link Image} object corresponding to the given ID
     */
    Image getImageById(Long id);

    /**
     * Deletes an image by its unique identifier.
     *
     * @param id the unique identifier of the image to delete
     */
    void deleteImageById(Long id);

    /**
     * Saves a list of images associated with a specific product.
     * This method processes each image file, creates a new {@link Image} entity,
     * sets relevant properties (such as file name, file type, image content, etc.),
     * and stores the image in the repository. It also generates a download URL
     * for each saved image and returns a list of {@link ImageDTO} objects representing
     * the saved images with their metadata.
     *
     * @param files The list of image files to be saved.
     * @param productId The ID of the product that the images are associated with.
     * @return A list of {@link ImageDTO} containing metadata about the saved images.
     * @throws RuntimeException if an error occurs while reading the files or saving them to the database.
     * @since 1.0.5
     */
    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);

    /**
     * Updates an existing image with new content.
     *
     * @param file    the new image file
     * @param imageId the unique identifier of the image to update
     */
    void updateImage(MultipartFile file, Long imageId);
}
