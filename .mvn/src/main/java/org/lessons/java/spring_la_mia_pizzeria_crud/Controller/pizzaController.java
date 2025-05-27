package org.lessons.java.spring_la_mia_pizzeria_crud.Controller;

import java.util.List;

import javax.naming.Binding;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/pizzas")
public class pizzaController {

    @Autowired
    private PizzaRepository repo;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = repo.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repo.findById(id).get());
        return "/pizzas/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Pizza> pizzas = repo.findByNameContainingOrDescriptionContaining(query, query);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("pizza") Pizza pizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }

        repo.save(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = repo.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("pizza") Pizza pizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        repo.save(pizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
        return "redirect:/pizzas";
    }
}
