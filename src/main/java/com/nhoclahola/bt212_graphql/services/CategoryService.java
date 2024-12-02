package com.nhoclahola.bt212_graphql.services;

import com.nhoclahola.bt212_graphql.entities.Category;
import com.nhoclahola.bt212_graphql.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesByUserId(Integer userId) {
        return categoryRepository.findByUserId(userId);
    }
}
