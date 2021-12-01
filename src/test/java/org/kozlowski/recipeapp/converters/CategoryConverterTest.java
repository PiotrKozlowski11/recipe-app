package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.domain.Category;
import org.kozlowski.recipeapp.services.CategoryService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CategoryConverterTest {
    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;

    CategoryConverter converter;

    @Mock
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        converter = new CategoryConverter(categoryService, new CategoryToCategoryCommand());
    }

    @Test
    void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryService.findById(anyLong())).thenReturn(categoryOptional);

        CategoryCommand categoryCommand = converter.convert("2");

        assertEquals(LONG_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, category.getDescription());

    }
}