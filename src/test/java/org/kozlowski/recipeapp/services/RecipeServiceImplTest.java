package org.kozlowski.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.converters.RecipeCommandToRecipe;
import org.kozlowski.recipeapp.converters.RecipeToRecipeCommand;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.repositories.RecipeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        //recipeService = new RecipeServiceImpl(recipeRepository, converter, recipeToRecipeCommand);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(1L)).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned,"Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
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
        verify(recipeRepository, never()).findById(anyLong());
    }


    @Test
    void saveRecipeCommand() {
    }

    @Test
    public void testDeleteById(){
        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById((anyLong()));
    }
}