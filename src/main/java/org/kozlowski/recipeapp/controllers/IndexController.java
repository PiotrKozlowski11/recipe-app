package org.kozlowski.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        log.debug("Index Controller, Index Page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
