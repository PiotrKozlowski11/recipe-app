package org.kozlowski.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }


    @Test
    void getRecipes() {

        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(new Recipe());

        //System.out.println(recipeService.getRecipes());

        when(recipeRepository.findAll()).thenReturn(recipeData);
        //System.out.println(recipeService.getRecipes());


        Set<Recipe> recipes = recipeService.getRecipes();


        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}