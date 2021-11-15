package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.domain.Ingredient;
import org.kozlowski.recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;
    public static final BigDecimal AMOUNT = new BigDecimal(2);
    public static final Long UOM_ID = 3L;
    public IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = converter.convert(ingredient);
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }
}