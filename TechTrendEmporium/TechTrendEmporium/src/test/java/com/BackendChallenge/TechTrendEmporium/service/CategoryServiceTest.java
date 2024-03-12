package com.BackendChallenge.TechTrendEmporium.service;

import com.BackendChallenge.TechTrendEmporium.entity.Category;
import com.BackendChallenge.TechTrendEmporium.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testFetchCategoryNames() {
        // Mock external API response
        String[] categoryArray = {"electronics", "jewelery", "men's clothing","women's clothing"};
        when(restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class))
                .thenReturn(categoryArray);

        // Call the method under test
        categoryService.fetchCategoryNames();

        // Verify that saveAll() method was called with correct arguments
        Mockito.verify(categoryRepository).saveAll(Mockito.anyList());

        // Capture the saved categories to verify later
        List<Category> capturedCategories = captureSavedCategories();

        // Mock the behavior of categoryRepository.findAll() to return the saved categories
        when(categoryRepository.findAll()).thenReturn(capturedCategories);

        // Get saved categories from the repository
        List<Category> retrievedCategories = categoryService.getAllCategories();
        System.out.println("retrievedCategories"+retrievedCategories);

        // Verify that correct categories were saved
        assertEquals(categoryArray.length, retrievedCategories.size());
        for (int i = 0; i < categoryArray.length; i++) {
            assertEquals(categoryArray[i], retrievedCategories.get(i).getName());
        }
    }

    // Helper method to capture the categories saved by the service
    private List<Category> captureSavedCategories() {
        ArgumentCaptor<List<Category>> captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(categoryRepository).saveAll(captor.capture());
        return captor.getValue();
    }




    @Test
    public void testGetAllCategories() {
        // Mock the behavior of categoryRepository.findAll()
        List<Category> categoriesList = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categoriesList);

        // Call the method under test
        List<Category> retrievedCategories = categoryService.getAllCategories();

        // Verify that findAll() method was called
        verify(categoryRepository).findAll();

        // Verify that correct categories were returned
        assertEquals(2, retrievedCategories.size());
    }



}



