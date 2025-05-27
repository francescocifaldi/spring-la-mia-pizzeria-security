package org.lessons.java.spring_la_mia_pizzeria_webapi.repository;

import org.lessons.java.spring_la_mia_pizzeria_webapi.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer> {
    // Custom query methods can be defined here if needed
}
