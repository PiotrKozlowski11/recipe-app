package org.kozlowski.recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.NotesCommand;
import org.kozlowski.recipeapp.domain.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    public static final String RECIPE_NOTES = "description";
    public static final Long LONG_VALUE = 1L;

    public NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(LONG_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = converter.convert(notesCommand);

        assertEquals(LONG_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());

    }
}