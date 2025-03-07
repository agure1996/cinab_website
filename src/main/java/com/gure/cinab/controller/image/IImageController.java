package com.gure.cinab.controller.image;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.response.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface defining the contract for image management operations in the application.
 * @since 1.0.6
 */
public interface IImageController {

    /**
     * Handles the upload and saving of multiple images associated with a product.
     *
     * @param files List of files to upload.
     * @param productId ID of the product to associate with the images.
     * @return A response entity containing the status of the upload operation.
     */
    ResponseEntity<ApiResponse> uploadImages( Long productId, List<MultipartFile> files);

    /**
     * Facilitates the download of an image by its ID.
     *
     * @param imageId ID of the image to download.
     * @return A resource containing the image data.
     * @throws SQLException If there is an error accessing the image blob from the database.
     */
    ResponseEntity<Resource> downloadImage(Long imageId) throws SQLException;

    /**
     * Updates an existing image with new file data.
     *
     * @param imageId ID of the image to update.
     * @param file New file data to update the image with.
     * @throws ResourceNotFoundException if no image with the specified ID exists.
     * @return A response entity indicating the status of the update operation.
     */
    ResponseEntity<ApiResponse> updateImage(Long imageId, MultipartFile file);

    /**
     * Deletes an image by its ID.
     *
     * @param imageId ID of the image to delete.
     * @throws ResourceNotFoundException if no image with the specified ID exists.
     * @return A response entity indicating the status of the deletion operation.
     */
    ResponseEntity<ApiResponse> deleteImage(Long imageId);
}
