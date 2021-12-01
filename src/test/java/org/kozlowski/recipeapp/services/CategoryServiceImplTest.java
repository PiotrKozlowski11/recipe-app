package org.kozlowski.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.converters.CategoryToCategoryCommand;
import org.kozlowski.recipeapp.domain.Category;
import org.kozlowski.recipeapp.repositories.CategoryRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, new CategoryToCategoryCommand());
    }

    @Test
    void findAllCommand() {
        HashSet<Category> categoriesData = new HashSet<>();
        categoriesData.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categoriesData);

        Set<CategoryCommand> categories = categoryService.findAllCommand();

        assertEquals(categories.size(), 1);
        verify(categoryRepository, times(1)).findAll();


    }
}