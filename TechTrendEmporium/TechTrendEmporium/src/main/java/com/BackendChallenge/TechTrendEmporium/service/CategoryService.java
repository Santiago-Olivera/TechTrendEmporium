package com.BackendChallenge.TechTrendEmporium.service;
import com.BackendChallenge.TechTrendEmporium.entity.Category;
import com.BackendChallenge.TechTrendEmporium.entity.Product;
import com.BackendChallenge.TechTrendEmporium.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    private static final String EXTERNAL_API_URL_CATEGORIES = "https://fakestoreapi.com/products/categories";

    public void fetchCategoryNames() {
        RestTemplate restTemplate = new RestTemplate();
        String[] categoryArray = restTemplate.getForObject(EXTERNAL_API_URL_CATEGORIES, String[].class);
        assert categoryArray != null;

        List<Category> categories = new ArrayList<>();
        for (String categoryName : categoryArray) {
            Category category = new Category();
            category.setName(categoryName);
            categories.add(category);
        }

        categoryRepository.saveAll(categories);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}


