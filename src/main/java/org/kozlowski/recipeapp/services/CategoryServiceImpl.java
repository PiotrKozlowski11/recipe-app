package org.kozlowski.recipeapp.services;

import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.converters.CategoryToCategoryCommand;
import org.kozlowski.recipeapp.domain.Category;
import org.kozlowski.recipeapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand converter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    @Override
    public Set<CategoryCommand> findAllCommand() {

        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(converter::convert).collect(Collectors.toSet());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
