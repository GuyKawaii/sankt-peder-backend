package com.example.sanktpederbackend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foto_id")
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
    @Column(name = "url")
    @NonNull
    @JsonProperty("url")
    private String url;

    public Foto() {
    }

    public Foto(int id, String name, String description, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
