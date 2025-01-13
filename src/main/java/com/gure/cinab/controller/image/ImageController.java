package com.gure.cinab.controller.image;

import com.gure.cinab.dto.ImageDTO;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Image;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("{api.prefix}/images")
public class ImageController implements IImageController {

    private final IImageService imageService;

    @Override
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            // Delegate the file upload and association with product to the service layer
            List<ImageDTO> imageDTOS = imageService.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("Upload Success", imageDTOS));
        } catch (Exception e) {
            // Handle any exceptions during upload
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Upload failed!", e.getMessage()));
        }
    }

    @Override
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        // Retrieve the image from the service layer
        Image image = imageService.getImageById(imageId);

        // Convert the image blob into a ByteArrayResource for streaming
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));

        // Prepare and return the response entity with the image file
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @Override
    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
        try {
            // Check if the image exists before updating
            Image image = imageService.getImageById(imageId);

            if (image != null) {
                // Update the image file through the service layer
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Updated successfully!", null));
            }
        } catch (ResourceNotFoundException e) {
            // Return a 404 response if the image is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        // Return an internal server error response if the update unexpectedly fails
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed!", INTERNAL_SERVER_ERROR));
    }

    @Override
    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        try {
            // Check if the image exists before attempting to delete
            Image image = imageService.getImageById(imageId);

            if (image != null) {
                // Delete the image using the service layer
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Deleted successfully!", null));
            }
        } catch (ResourceNotFoundException e) {
            // Return a 404 response if the image is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        // Return an internal server error response if the deletion fails
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed!", INTERNAL_SERVER_ERROR));
    }
}
