package com.example.sanktpederbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    @NonNull
    @JsonProperty("id")
    private int id;
    @Column(name = "name")
    @NonNull
    @JsonProperty("name")
    private String name;
    @Column(name = "description")
    @NonNull
    @JsonProperty("description")
    private String description;
    @Column(name = "price")
    @NonNull
    @JsonProperty("price")
    private String price;

    public Menu() {
    }

    public Menu(int id, String name, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
