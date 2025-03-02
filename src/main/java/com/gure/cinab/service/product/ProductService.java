package com.gure.cinab.service.product;

import com.gure.cinab.dto.ImageDTO;
import com.gure.cinab.dto.ProductDTO;
import com.gure.cinab.exceptions.ResourceNotFoundException;
import com.gure.cinab.model.Category;
import com.gure.cinab.model.Image;
import com.gure.cinab.model.Product;

import com.gure.cinab.repository.CategoryRepository;
import com.gure.cinab.repository.ImageRepository;
import com.gure.cinab.repository.ProductRepository;
import com.gure.cinab.request.product.AddProductRequest;

import com.gure.cinab.request.product.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing products in the system.
 * This class contains business logic for adding, updating, deleting, and retrieving products.
 * It interacts with the {@link ProductRepository} for data persistence and the {@link CategoryRepository}
 * for managing product categories.
 *
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(AddProductRequest request) {
        //Check to see if category is found in db
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        //if yes, set it as the new product category
        request.setCategory(category);
        //if no then save it as a new category then set it as new product category
        return productRepository.save(createProduct(request, category));
    }

    /**
     * Creates a new Product using the provided request data and category.
     *
     * @param request  the request containing the product details
     * @param category the category to assign to the new product
     * @return the newly created Product
     */
    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("Product not found!");
                        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    /**
     * Updates an existing product with the details from the provided update request.
     *
     * @param existingProduct the product to update
     * @param request         the update request containing new product details
     * @return the updated product
     */
    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
    @Override
    public List<ProductDTO> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDTO).toList();
    }

    @Override
    public ProductDTO convertToDTO(Product product) {

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDTO> imageDTOs = images
                .stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .toList();

        productDTO.setImages(imageDTOs);
        return productDTO;
    }

}
