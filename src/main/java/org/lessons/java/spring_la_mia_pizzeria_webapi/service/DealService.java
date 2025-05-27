package org.lessons.java.spring_la_mia_pizzeria_webapi.service;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_webapi.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    public Deal create(Deal deal) {
        return dealRepository.save(deal);
    }

    public Deal update(Deal deal) {
        return dealRepository.save(deal);
    }

    public void delete(Deal dealToDelete) {
        dealRepository.delete(dealToDelete);
    }

    public void deleteById(Integer id) {
        Deal dealToDelete = dealRepository.findById(id).orElseThrow(() -> new RuntimeException("Deal not found"));
        delete(dealToDelete);
    }

    public Deal getById(Integer id) {
        Optional<Deal> dealOptional = dealRepository.findById(id);
        if (dealOptional.isEmpty()) {
            // not found with response entity
        }
        return dealOptional.get();
    }
}
