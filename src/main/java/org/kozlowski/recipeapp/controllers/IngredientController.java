package org.kozlowski.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.commands.IngredientCommand;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.commands.UnitOfMeasureCommand;
import org.kozlowski.recipeapp.domain.UnitOfMeasure;
import org.kozlowski.recipeapp.services.IngredientService;
import org.kozlowski.recipeapp.services.RecipeService;
import org.kozlowski.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipeID: "+recipeId);

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id,
                                       Model model){
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId),Long.valueOf(id));
        model.addAttribute("ingredient",ingredientCommand);

        return "recipe/ingredient/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId,
                            Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());


        model.addAttribute("ingredient",ingredientCommand);
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId,
                                   @PathVariable String id,
                                   Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId),Long.valueOf(id)));

        model.addAttribute("uomList",unitOfMeasureService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        //RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }
}
