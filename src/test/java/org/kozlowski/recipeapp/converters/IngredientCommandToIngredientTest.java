package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.commands.UnitOfMeasureCommand;
import org.kozlowski.recipeapp.domain.Ingredient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = 1L;
    public static final BigDecimal AMOUNT = new BigDecimal(2);
    public static final Long UOM_ID = 3L;
    public IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand command = new IngredientCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        Ingredient ingredient = converter.convert(command);
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }
}