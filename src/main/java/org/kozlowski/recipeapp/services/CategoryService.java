package org.kozlowski.recipeapp.services;

import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.domain.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Set<CategoryCommand> findAllCommand();

    Optional<Category> findById(Long valueOf);
}
