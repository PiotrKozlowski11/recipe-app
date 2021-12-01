package org.kozlowski.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.commands.RecipeCommand;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.exceptions.NotFoundException;
import org.kozlowski.recipeapp.services.CategoryService;
import org.kozlowski.recipeapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
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
        model.addAttribute("allCategories", categoryService.findAllCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid
                               @ModelAttribute("recipe") RecipeCommand command, BindingResult result) {

        System.out.println(command.getDescription());
        command.getCategories().forEach(System.out::println);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show/";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);


        return modelAndView;
    }


}
