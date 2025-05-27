package org.lessons.java.spring_la_mia_pizzeria_security.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_security.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_security.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);
        if (ingredientOptional.isEmpty()) {
            // not found with response entity
        }
        return ingredientOptional.get();
    }

    public void delete(Ingredient ingredientToDelete) {
        ingredientRepository.delete(ingredientToDelete);
    }

    public void deleteById(Integer id) {
        Ingredient ingredientToDelete = getById(id);
        delete(ingredientToDelete);
    }
}
