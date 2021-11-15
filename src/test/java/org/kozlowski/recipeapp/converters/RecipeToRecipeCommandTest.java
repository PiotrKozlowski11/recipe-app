package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.domain.*;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NotesToNotesCommand());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(LONG_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(ID_ING1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ID_ING2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);


        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(ID_NOTES);

        Category category1 = new Category();
        category1.setId(ID_CAT1);

        Category category2 = new Category();
        category2.setId(ID_CAT2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        RecipeCommand recipeCommand = converter.convert(recipe);


        assertEquals(LONG_VALUE, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getCategories().size());
    }
}