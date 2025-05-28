package org.lessons.java.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_security.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.service.IngredientService;
import org.lessons.java.spring_la_mia_pizzeria_security.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Authentication auth, Model model) {
        List<Pizza> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", auth.getName());
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizzas/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Pizza> pizzas = pizzaService.search(query);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("pizza") Pizza pizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/create-or-edit";
        }

        pizzaService.create(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaService.getById(id);
        model.addAttribute("pizza", pizza);
        model.addAttribute("edit", true);
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("pizza") Pizza pizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/create-or-edit";
        }

        pizzaService.update(pizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/deals")
    public String createDeal(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        Deal deal = new Deal();
        deal.setPizza(pizzaService.getById(id));
        model.addAttribute("deal", deal);
        return "deals/create-or-edit";
    }
}
