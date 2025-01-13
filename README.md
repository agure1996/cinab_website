# Cinab Website - Changelog

## Patch Notes - Version 1.0.7 (current)

### Changes and Updates:
1. **Category Controller Enhancements**:
  - Created `CategoryController` implementation adhering to the `ICategoryController` interface.
  - Added endpoints to handle CRUD operations for categories:
    - Fetch all categories (`GET /categories/all`)
    - Fetch category by ID (`GET /categories/category/{id}`)
    - Fetch category by name (`GET /categories/category/{name}`)
    - Add a new category (`POST /categories/category/add`)
    - Update an existing category (`PUT /categories/category/{id}/update`)
    - Delete a category (`DELETE /categories/category/{id}/delete`)

2. **Improved Modular Structure**:
  - Implemented a clear contract-based approach with the use of `ICategoryController`.
  - Dependency injection applied via `@RequiredArgsConstructor` for the `ICategoryService`, ensuring clean separation of concerns.

3. **API Response Consistency**:
  - Standardized the structure of all responses using the `ApiResponse` wrapper for better client-side consumption.
  - Handled exceptions gracefully with proper HTTP status codes:
    - `200 OK` for successful operations.
    - `404 Not Found` for missing resources.
    - `409 Conflict` for addition conflicts.
    - `500 Internal Server Error` for unexpected errors.

4. **Enhanced Error Handling**:
  - Incorporated specific error responses like `ResourceNotFoundException` to provide meaningful feedback to API consumers.

5. **Documentation Improvements**:
  - Added inline comments in `CategoryController` for better readability and understanding of implementation logic.

---

### Summary:
This patch focuses on enhancing the category management module by implementing robust CRUD operations with a modular design approach. It improves API response consistency and readability, ensuring ease of maintenance and extension in future updates.


## Version 1.0.6 (January 13, 2025)

- **Fixes**:
  - Added versioning and documentation to the `ApiResponse` class for comprehensive readability and understanding of class.

- **Improvements**:
   **Created Image Controller Contract and Implementation**
   - Added `IImageController` interface to define the contract for image-related endpoints.
   - Implemented `ImageController` class to handle CRUD operations for images, including:
     - **Uploading Images (`saveImages`)**: Handles multiple image file uploads and associates them with a product.
     - **Downloading Images (`downloadImage`)**: Allows downloading an image by its ID.
     - **Updating Images (`updateImage`)**: Updates an existing image's content by its ID.
     - **Deleting Images (`deleteImage`)**: Deletes an image by its ID.

   **Preparation for Product and Category Controllers**
   - Prepared the groundwork for upcoming functionality in the product and category controllers.
   - Ensured services and endpoints are modular and ready for extension.

## Version 1.0.5 (January 12, 2025)

- **Fixes**:
    - Fixed bug in `saveImages` method for handling multiple image uploads in the `IImageService` interface. Ensured that each image receives a download URL and is correctly stored in the database.
    - Improved exception handling for file upload errors (IOException, SQLException).
    - Fixed issue with `findByBrandName` method in `ProductRepository` (incorrect query definition for brand).

- **Improvements**:
    - Enhanced `ImageDTO` to store image details (ID, name, download URL) for better response formatting.
    - Refined the `ProductService` to work seamlessly with the `ImageService` for image handling.
    - Optimized the `saveImages` method in `ImageService` to handle large sets of images efficiently by reducing unnecessary database calls.
    - Simplified the response structure in `saveImages` to return a list of `ImageDTO` objects.

## Version 1.0.4 (January 9, 2025)

- **Fixes**:
    - Resolved issues with missing required field handling during image upload. Ensured that the `Image` object properties are set properly before saving.
    - Fixed invalid mapping for product-to-image association in `Image` entity.

- **Improvements**:
    - Refined the `saveImage` method for single image uploads to return a more comprehensive response (including download URL).
    - Improved error handling for database and file system issues during image storage.

## Version 1.0.3 (January 8, 2025)

- **Fixes**:
    - Corrected broken routes and endpoints in the image controller for both single and bulk image upload scenarios.
    - Fixed issue with image file size handling during upload.

- **Improvements**:
    - Enhanced `ProductService` and `ImageService` communication for proper validation of image existence before proceeding with save.
    - Added better logging for error tracking when images fail to upload or process.

## Version 1.0.2 (January 7, 2025)

- **Fixes**:
    - Fixed database connection issues related to the `ProductRepository` during data fetching from PostgreSQL.
    - Resolved bug where images were not correctly associated with products after upload.

- **Improvements**:
    - Introduced a robust validation layer to ensure only valid images are being uploaded and stored.
    - Improved method signatures in the `IImageService` interface for better flexibility and clarity.

## Version 1.0.1 (January 6, 2025)

- **Fixes**:
    - Corrected incorrect `ProductRepository` method for brand-based product retrieval (`findByBrandName`).
    - Fixed inconsistent behavior when saving image metadata and linking it to product entries.

- **Improvements**:
    - Created initial setup for handling CRUD operations in `CategoryService` and `ProductService`.
    - Added initial implementation of the `Category` entity to support basic category management in the application.
