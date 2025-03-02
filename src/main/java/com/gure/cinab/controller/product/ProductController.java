package com.gure.cinab.controller.product;

import com.gure.cinab.dto.ProductDTO;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Product;
import com.gure.cinab.request.product.AddProductRequest;
import com.gure.cinab.request.product.ProductUpdateRequest;
import com.gure.cinab.response.ApiResponse;
import com.gure.cinab.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@AllArgsConstructor

@RequestMapping("${api.prefix}/products")  // API endpoint with versioning or prefix
public class ProductController implements IProductController {


    private final IProductService productService;  // Injecting the product service

    @Override
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> productsList = productService.getAllProducts();  // Fetching all products
            List<ProductDTO> convertedProducts = productService.getConvertedProducts(productsList);  // Fetching all products
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(new ApiResponse("Found products!", convertedProducts));  // Successful response
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));  // Handling error if products not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unexpected error: " + e.getMessage(), null));
        }
    }

    @Override
    @Transactional
    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product theProduct = productService.getProductById(productId);
            ProductDTO convertedProduct = productService.convertToDTO(theProduct); // Fetching product by ID
            return ResponseEntity.ok(new ApiResponse("Success! ", convertedProduct));  // Successful response
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));  // Handling error if product not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unexpected error: " + e.getMessage(), null));
        }
    }

    @Override// Temporarily remove restriction
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest newProduct) {
        try {
            Product theNewProduct = productService.addProduct(newProduct);  // Adding new product
            return ResponseEntity.ok(new ApiResponse("Product added successfully! ", theNewProduct));  // Successful addition
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));  // Handling generic errors
        }
    }

    @Override
    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest product) {
        try {
            // Updating product details
            Product updatedProduct = productService.updateProduct(product, productId);
            ProductDTO convertedProduct = productService.convertToDTO(updatedProduct);
            // Return the updated product details
            return ResponseEntity.ok(new ApiResponse("Updated product successfully!", convertedProduct));  // Successful update
        } catch (ResourceNotFoundException e) {
            // Handling cases where the product is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));  // Error response if product not found
        }
    }

    @Override
    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            // Delete the product by ID
            productService.deleteProduct(productId);
            // Return success response after deletion
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully!", null));  // Successful deletion
        } catch (ResourceNotFoundException e) {
            // Handling cases where the product is not found
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));  // Error response if product not found
        }
    }

    @Override
    @Transactional
    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            // Fetch products based on both brand and name
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                // Handling cases where no products are found for the given brand and name
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null));  // Error response
            }
            List<ProductDTO> convertedProduct = productService.getConvertedProducts(products);

            return ResponseEntity.ok(new ApiResponse("Success!", convertedProduct));  // Successful response
        } catch (Exception e) {
            // Handling other random error cases
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));  // Generic error handling
        }
    }

    @Override
    @Transactional
    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            // Fetch products based on both brand and category
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                // Handling cases where no products are found for the given brand and name
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null));  // Error response
            }
            List<ProductDTO> convertedProduct = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success!", convertedProduct));  // Successful response
        } catch (Exception e) {
            // Handling other random error cases
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));  // Generic error handling
        }
    }

    @Override
    @Transactional
    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        try {
            // Fetch products based on product name
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                // Handling cases where no products are found by name
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found!", null));  // Error response
            }
            List<ProductDTO> convertedProduct = productService.getConvertedProducts(products);

            return ResponseEntity.ok(new ApiResponse("Success!", convertedProduct));  // Successful response
        } catch (Exception e) {
            // Handling other random error cases
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));  // Generic error handling
        }
    }

    @Override
    @Transactional
    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            // Fetch products based on brand
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()) {
                // Handling cases where no products are found for the given brand
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null));  // Error response
            }
            List<ProductDTO> convertedProduct = productService.getConvertedProducts(products);

            return ResponseEntity.ok(new ApiResponse("Success!", convertedProduct));  // Successful response
        } catch (Exception e) {
            // Handling other random error cases
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));  // Generic error handling
        }
    }

    @Override
    @Transactional
    @GetMapping("/products/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Total products: ", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @Override
    @Transactional
    @GetMapping("/products/{category}/all/products")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
        try {
            // Fetch products based on category
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                // Handling cases where no products are found for the given category
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null));  // Error response
            }
            List<ProductDTO> convertedProduct = productService.getConvertedProducts(products);

            return ResponseEntity.ok(new ApiResponse("Success!", convertedProduct));  // Successful response
        } catch (Exception e) {
            // Handling error if products are not found for category
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));  // Error response
        }
    }
}
