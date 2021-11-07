package org.kozlowski.recipeapp.repositories;

import org.kozlowski.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
