package org.lessons.java.spring_la_mia_pizzeria_security.controller;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_security.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientService.create(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Ingredient ingredient = ingredientService.getById(id);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }

        ingredientService.update(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredient = ingredientService.getById(id);
        for (Pizza pizza : ingredient.getPizzas()) {
            pizza.getIngredients().remove(ingredient);
        }
        ingredientService.delete(ingredient);
        return "redirect:/ingredients";
    }

}
