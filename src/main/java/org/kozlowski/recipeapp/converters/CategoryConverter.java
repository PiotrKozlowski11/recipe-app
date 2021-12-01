package org.kozlowski.recipeapp.converters;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.domain.Category;
import org.kozlowski.recipeapp.services.CategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CategoryConverter implements Converter<String, CategoryCommand> {
    private final CategoryService categoryService;
    private final CategoryToCategoryCommand converter;

    public CategoryConverter(CategoryService categoryService, CategoryToCategoryCommand converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @Override
    public CategoryCommand convert(String source) {
        Optional<Category> categoryOptional = categoryService.findById(Long.valueOf(source));
        if (categoryOptional.isEmpty()) {
            log.debug("Category not found for id: " + source);
            throw new RuntimeException("Category not found for id: " + source);
        }

        return converter.convert(categoryOptional.get());
    }
}
