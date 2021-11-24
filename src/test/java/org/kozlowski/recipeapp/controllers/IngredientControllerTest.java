package org.kozlowski.recipeapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.services.IngredientService;
import org.kozlowski.recipeapp.services.RecipeService;
import org.kozlowski.recipeapp.services.UnitOfMeasureService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;


    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;


    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listIngredients() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.findCommandById(2L)).thenReturn(command);

        mockMvc.perform(get("/recipe/2/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService,times(1)).findCommandById(anyLong());

    }

    @Test
    void showRecipeIngredient() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(3L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe/3/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/3/ingredient/2/show"));

    }

    @Test
    void updateIngredientTest() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(2L);
        command.setRecipeId(3L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(command);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/3/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient","uomList"));

    }

    @Test
    void newIngredientTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/2/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient", "uomList"));

    }

    @Test
    void deleteIngredientById() throws Exception {

        mockMvc.perform(get("/recipe/2/ingredient/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients/"));

        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());


    }
}