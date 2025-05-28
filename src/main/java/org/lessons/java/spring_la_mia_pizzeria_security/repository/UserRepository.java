package org.lessons.java.spring_la_mia_pizzeria_security.repository;

import org.lessons.java.spring_la_mia_pizzeria_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
