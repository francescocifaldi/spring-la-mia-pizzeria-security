package org.lessons.java.spring_la_mia_pizzeria_security.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    @GetMapping("/search")
    public List<Pizza> search(@RequestParam("query") String query) {
        return pizzaService.search(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @RequestBody Pizza pizza) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pizza.setId(id);
        return new ResponseEntity<>(pizzaService.update(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaService.findById(id);
        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pizzaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
