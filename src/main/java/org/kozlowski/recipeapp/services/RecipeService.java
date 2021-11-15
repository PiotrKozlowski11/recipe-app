package org.kozlowski.recipeapp.services;

import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
