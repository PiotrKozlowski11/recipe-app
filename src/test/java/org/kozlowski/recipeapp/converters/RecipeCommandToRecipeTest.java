package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.CategoryCommand;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.domain.Difficulty;
import org.kozlowski.recipeapp.domain.Notes;
import org.kozlowski.recipeapp.domain.Recipe;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 2;
    public static final Integer COOK_TIME = 3;
    public static final Integer SERVINGS = 4;
    public static final String SOURCE = "SOURCE";
    public static final String URL = "URL";
    public static final String DIRECTIONS = "DIRECTIONS";
    private static final Long ID_ING1 = 5L;
    private static final Long ID_ING2 = 6L;
    private static final Long ID_NOTES = 7L;
    private static final Long ID_CAT1 = 8L;
    private static final Long ID_CAT2 = 9L;
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(), new NotesCommandToNotes());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(LONG_VALUE);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(ID_ING1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(ID_ING2);

        recipeCommand.getIngredients().add(ingredient1);
        recipeCommand.getIngredients().add(ingredient2);


        recipeCommand.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(ID_NOTES);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(ID_CAT1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(ID_CAT2);

        recipeCommand.getCategories().add(category1);
        recipeCommand.getCategories().add(category2);

        Recipe recipe = converter.convert(recipeCommand);


        assertEquals(LONG_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getCategories().size());
    }
}