package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Category;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.repository.CategoryRepository;
import com.BackendChallenge.TechTrendEmporium.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private static final String EXTERNAL_API_URL_PRODUCTS = "https://fakestoreapi.com/products";

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void fetchAndSaveProducts() {
        // Fetch products from external API
        RestTemplate restTemplate = new RestTemplate();
        Product[] products = restTemplate.getForObject(EXTERNAL_API_URL_PRODUCTS, Product[].class);

        // Set category ID for each product before saving to the database
        assert products != null;
        for (Product product : products) {
            String categoryName = product.getCategory();
            // Find category by name
            Category category = categoryRepository.findByName(categoryName);
            if (category != null) {
                // Set categoryId in the product entity
                product.setCategoryId((category.getId()));
            } else {
                //  where category is not found , **could throw an exception if wanted
                product.setCategoryId(0L);
            }
        }

        // Save products to the database
        if (products.length > 0 && productRepository.count() == 0) {
            productRepository.saveAll(Arrays.asList(products));
        }
    }

    public List<Product> getAllProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return productPage.getContent();
    }


    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getAllProductsSortedByPrice(String sortOrder) {
        Sort sort = Sort.by("price");
        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        }
        return productRepository.findAll(sort);
    }

    public List<Product> getAllProductsSortedByTitle(String sortBy) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortBy != null && sortBy.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = Sort.by(direction, "title"); // Sort by title field
        return productRepository.findAll(sort);
    }


}

