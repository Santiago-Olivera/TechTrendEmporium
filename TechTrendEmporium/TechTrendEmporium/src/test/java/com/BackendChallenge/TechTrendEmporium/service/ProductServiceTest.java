package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testSomething() {
        System.out.println("testing!");// Test logic goes here""
    }

    @Test
    public void testFetchAndSaveProducts() {
        // Mock external API response
        RestTemplate restTemplate = new RestTemplate();
        Product[] products = restTemplate.getForObject("https://fakestoreapi.com/products", Product[].class);

        // Mock the behavior of productRepository.saveAll()
        when(productRepository.saveAll(any())).thenReturn(Arrays.asList(products));

        // Call the method under test
        productService.fetchAndSaveProducts();

        // Verify that saveAll() method was called with correct arguments
        verify(productRepository, times(1)).saveAll(Arrays.asList(products));
    }

    @Test
    public void testGetAllProducts() {
        // Mock the behavior of productRepository.findAll()
        List<Product> productList = Arrays.asList(new Product(), new Product()); // Example products
        when(productRepository.findAll()).thenReturn(productList);

        // Call the method under test
        List<Product> retrievedProducts = productService.getAllProducts();

        // Verify that findAll() method was called
        verify(productRepository, times(1)).findAll();

        // Verify that correct products were returned
        assertEquals(2, retrievedProducts.size()); // Assuming two products were mocked
    }
}

