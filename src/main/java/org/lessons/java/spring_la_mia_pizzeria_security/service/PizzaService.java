package org.lessons.java.spring_la_mia_pizzeria_security.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_security.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_security.repository.DealRepository;
import org.lessons.java.spring_la_mia_pizzeria_security.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private DealRepository dealRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) {
            // not found with response entity
        }
        return pizzaOptional.get();
    }

    public List<Pizza> search(String query) {
        return pizzaRepository.findByNameContainingOrDescriptionContaining(query, query);
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizzaToDelete) {
        for (Deal dealToDelete : pizzaToDelete.getDeals()) {
            dealRepository.delete(dealToDelete);
        }
        pizzaRepository.delete(pizzaToDelete);
    }

    public void deleteById(Integer id) {
        Pizza pizzaToDelete = getById(id);

        for (Deal dealToDelete : pizzaToDelete.getDeals()) {
            dealRepository.delete(dealToDelete);
        }
        pizzaRepository.delete(pizzaToDelete);
    }
}
