package com.BackendChallenge.TechTrendEmporium.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.BackendChallenge.TechTrendEmporium.TechTrendEmporiumApplication;
import com.BackendChallenge.TechTrendEmporium.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

import com.BackendChallenge.TechTrendEmporium.entity.Category;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.repository.CategoryRepository;
import com.BackendChallenge.TechTrendEmporium.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product[] fakeProducts;
    private Category dummyCategory;
    //private static final String EXTERNAL_API_URL_PRODUCTS = "https://fakestoreapi.com/products";

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, categoryRepository);

        // Setting up fake products
        fakeProducts = new Product[]{
                new Product(),
                new Product()
        };
        fakeProducts[0].setId(345L);
        fakeProducts[0].setTitle("John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet");
        fakeProducts[0].setPrice(695.0);
        fakeProducts[0].setCategory("jewelery");
        fakeProducts[0].setCategoryId(30L);
        fakeProducts[0].setDescription("From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.");
        fakeProducts[0].setImage("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg");
        //fakeProducts[0].setRating(new Rating(4.6, 400));

        fakeProducts[1].setId(346L);
        fakeProducts[1].setTitle("Solid Gold Petite Micropave");
        fakeProducts[1].setPrice(168.0);
        fakeProducts[1].setCategory("jewelery");
        fakeProducts[1].setCategoryId(30L);
        fakeProducts[1].setDescription("Satisfaction Guaranteed. Return or exchange any order within 30 days.Designed and sold by Hafeez Center in the United States. Satisfaction Guaranteed. Return or exchange any order within 30 days.");
        fakeProducts[1].setImage("https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg");

        dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyCategory.setName("men's clothing");
        //akeProducts[1].setRating(new Rating(3.9, 70));
    }

    @Test
    void testFetchAndSaveProducts() {
        // Mocking restTemplate to return fake products

        when(productRepository.count()).thenReturn(0L);

        /*when(restTemplate.getForObject(EXTERNAL_API_URL_PRODUCTS, Product[].class))
                .thenReturn(fakeProducts);*/

        when(categoryRepository.findByName("men's clothing")).thenReturn(dummyCategory);

        // Mocking product repository saveAll method
        when(productRepository.saveAll(any())).thenReturn(Arrays.asList(fakeProducts));

        // Calling the method under test
        productService.fetchAndSaveProducts();


        verify(productRepository, times(1)).count();
        verify(categoryRepository, times(4)).findByName("men's clothing");


        ArgumentCaptor<List<Product>> captor;
        captor = ArgumentCaptor.forClass(List.class);
        verify(productRepository).saveAll(captor.capture());
        List<Product> savedProducts = captor.getValue();
        assertEquals(20, savedProducts.size());
    }

    @Test
    void testGetAllProducts() {
        // Given
        int page = 0;
        int size = 10;
        List<Product> expectedProducts = new ArrayList<>(); // Provide expected products here

        Page<Product> productPage = new PageImpl<>(expectedProducts);
        when(productRepository.findAll(PageRequest.of(page, size))).thenReturn(productPage);

        // When
        List<Product> actualProducts = productService.getAllProducts(page, size);

        // Then
        assert actualProducts != null;
        assert actualProducts.equals(expectedProducts);
    }

    @Test
    void testGetProductsByCategory() {
        // Given
        String category = "Test Category";
        List<Product> expectedProducts = new ArrayList<>(); // Provide expected products here

        when(productRepository.findByCategory(category)).thenReturn(expectedProducts);

        // When
        List<Product> actualProducts = productService.getProductsByCategory(category);

        // Then
        assert actualProducts != null;
        assert actualProducts.equals(expectedProducts);
    }
}
