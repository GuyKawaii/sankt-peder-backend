package com.example.sanktpederbackend.model;

import com.example.sanktpederbackend.model.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MenuCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_card_id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "menuCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuItems;

    public MenuCard() {
        this.menuItems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Menu> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(Menu menuItem) {
        menuItems.add(menuItem);
        menuItem.setMenuCard(this);
    }

    public void removeMenuItem(Menu menuItem) {
        menuItems.remove(menuItem);
        menuItem.setMenuCard(null);
    }
    public List<Menu> getMenus() {
        return menuItems;
    }

}
