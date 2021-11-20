package org.kozlowski.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        Recipe recipeReturned = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipeReturned);
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        System.out.println("New Recipe");

        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId()+"/show/";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
