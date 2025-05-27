package org.lessons.java.spring_la_mia_pizzeria_security.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Il titolo è obbligatorio")
    @Size(min = 3, max = 50, message = "Il titolo deve essere tra 3 e 50 caratteri")
    private String title;

    @NotNull(message = "La data di inizio è obbligatoria")
    @PastOrPresent(message = "La data di inizio deve essere nel passato o presente")
    private LocalDate startDate;

    @NotNull(message = "La data di fine è obbligatoria")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private Pizza pizza;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
