package com.gure.cinab.controller.category;

import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Category;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService; // Dependency injection for category service

    @Override
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            // Retrieve a list of all categories
            List<Category> categoryList = categoryService.getAllCategories();
            // Return the list with a success message
            return ResponseEntity.ok(new ApiResponse("Found categories", categoryList));
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }

    @Override
    @PostMapping("/category/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
        try {
            // Add a new category
            Category theCategory = categoryService.addCategory(name);
            // Return success response with the added category details
            return ResponseEntity.ok(new ApiResponse("Success", theCategory));
        } catch (Exception e) {
            // Handle any conflicts (e.g., category already exists)
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            // Fetch a category by its ID
            Category theCategory = categoryService.getCategoryById(id);
            // Return the category if found
            return ResponseEntity.ok(new ApiResponse("Category found!", theCategory));
        } catch (Exception e) {
            // Handle cases where the category is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @GetMapping("/category/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        try {
            // Fetch a category by its name
            Category theCategory = categoryService.getCategoryByName(name);
            // Return the category if found
            return ResponseEntity.ok(new ApiResponse("Category found!", theCategory));
        } catch (Exception e) {
            // Handle cases where the category is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            // Delete the category by ID
            categoryService.deleteCategory(id);
            // Return success response after deletion
            return ResponseEntity.ok(new ApiResponse("Deleted successfully!", null));
        } catch (Exception e) {
            // Handle cases where the category is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            // Update the category details
            Category updatedCategory = categoryService.updateCategory(category, id);
            // Return the updated category details
            return ResponseEntity.ok(new ApiResponse("Updated Category successfully!", updatedCategory));
        } catch (ResourceNotFoundException e) {
            // Handle cases where the category is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
