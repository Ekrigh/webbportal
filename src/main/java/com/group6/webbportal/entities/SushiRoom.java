package com.group6.webbportal.entities;

import jakarta.persistence.*;

@Entity
@Table( name = "sushi_rooms")
public class SushiRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "max_guests")
    private int maxGuests;
    @Column(name = "technical_equipment")
    private String technicalEquipment;

    public SushiRoom() {
    }

    public SushiRoom(Long id, String name, int maxGuests, String technicalEquipment) {
        this.id = id;
        this.name = name;
        this.maxGuests = maxGuests;
        this.technicalEquipment = technicalEquipment;
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

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getTechnicalEquipment() {
        return technicalEquipment;
    }

    public void setTechnicalEquipment(String technicalEquipment) {
        this.technicalEquipment = technicalEquipment;
    }
}
