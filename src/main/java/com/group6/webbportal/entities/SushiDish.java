package com.group6.webbportal.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sushidishes")
public class SushiDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price_sek")
    private Double priceSEK;

    public SushiDish() {
    }

    public SushiDish(Long id, String name, String description, Double priceSEK) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceSEK = priceSEK;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(Double priceSEK) {
        this.priceSEK = priceSEK;
    }
}