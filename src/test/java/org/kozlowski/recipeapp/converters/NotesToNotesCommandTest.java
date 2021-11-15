package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.NotesCommand;
import org.kozlowski.recipeapp.domain.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {
    public static final String RECIPE_NOTES = "description";
    public static final Long LONG_VALUE = 1L;

    public NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = converter.convert(notes);

        assertEquals(LONG_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());

    }
}