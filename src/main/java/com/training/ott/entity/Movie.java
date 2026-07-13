package com.training.ott.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String requiredPlan;

    public Movie() {
    }

    public Movie(String title, String genre, String requiredPlan) {
        this.title = title;
        this.genre = genre;
        this.requiredPlan = requiredPlan;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRequiredPlan() {
        return requiredPlan;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRequiredPlan(String requiredPlan) {
        this.requiredPlan = requiredPlan;
    }
}
