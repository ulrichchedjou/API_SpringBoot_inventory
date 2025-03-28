package com.productInventory.inventory.service;

import com.productInventory.inventory.DTOs.CreateProductRequest;
import com.productInventory.inventory.DTOs.ProductResponse;
import com.productInventory.inventory.DTOs.UpdateProductRequest;
import com.productInventory.inventory.exception.ProductNotFoundException;
import com.productInventory.inventory.model.Product;
import com.productInventory.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product = productRepository.save(product);
        return mapToDTO(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getQuantity() != null) product.setQuantity(request.getQuantity());

        return mapToDTO(productRepository.save(product));
    }

    private ProductResponse mapToDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setLowStock(product.getQuantity() < 5);
        return dto;
    }

    public void deleteProduct(Long id) {
        // Vérifie l'existence du produit avant suppression
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

    public List<ProductResponse> getLowStockProducts() {
        return productRepository.findByQuantityLessThan(5).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


}
