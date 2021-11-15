package org.kozlowski.recipeapp.controllers;

import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        Recipe recipeReturned = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe",recipeReturned);
        return "recipe/show";
    }
}
