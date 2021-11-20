package org.kozlowski.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.converters.IngredientCommandToIngredient;
import org.kozlowski.recipeapp.converters.IngredientToIngredientCommand;
import org.kozlowski.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import org.kozlowski.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.kozlowski.recipeapp.domain.Ingredient;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.repositories.RecipeRepository;
import org.kozlowski.recipeapp.repositories.UnitOfMeasureRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    RecipeRepository recipeRepository;



    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);


        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand, ingredientCommandToIngredient, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(1L)).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(3L,ingredientCommand.getId());
        assertEquals(1L,ingredientCommand.getRecipeId());

        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    void saveIngredientCommandTest() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Recipe recipe = new Recipe();
        recipe.setId(2L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }
}