package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private static final String EXTERNAL_API_URL_PRODUCTS = "https://fakestoreapi.com/products";
    public void fetchAndSaveProducts() {
        // Fetch products from external API
        /*
        *  RestTemplate is a class in spring that
           makes it easy to consume RESTful web services.
        */
        RestTemplate restTemplate = new RestTemplate();
        Product[] products = restTemplate.getForObject(EXTERNAL_API_URL_PRODUCTS, Product[].class);
        /*
        The getForObject() method performs a synchronous HTTP GET operation, expecting to receive
        an array of Product objects in response.
        We specify the type Product[].class to indicate that we expect an array of Product objects.
        */

        // Save products to the database
        assert products != null; //ensure products array is not null
        productRepository.saveAll(Arrays.asList(products));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
