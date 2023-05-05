package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.model.MenuCard;
import com.example.sanktpederbackend.service.MenuCardService;
import com.example.sanktpederbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1")
public class MenuCardController {
    private static final Logger log = LoggerFactory.getLogger(MenuCardController.class);

    private final MenuCardService menuCardService;
    private final MenuService menuService;

    @Autowired
    public MenuCardController(MenuCardService menuCardService, MenuService menuService) {
        this.menuCardService = menuCardService;
        this.menuService = menuService;
    }

    @GetMapping("/menu-cards")
    public List<MenuCard> getMenuCards() {
        return menuCardService.getMenuCards();
    }

    @GetMapping("/menu-cards/{id}")
    public ResponseEntity<MenuCard> getMenuCardById(@PathVariable Integer id) {
        Optional<MenuCard> menuCard = menuCardService.getMenuCardById(id);
        if (menuCard.isPresent()) {
            return ResponseEntity.ok(menuCard.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/menu-cards/{id}/menu-items")
    public List<Menu> getMenuItemsByMenuCardId(@PathVariable Integer id) {
        Optional<MenuCard> optionalMenuCard = menuCardService.getMenuCardById(id);
        if (optionalMenuCard.isPresent()) {
            MenuCard menuCard = optionalMenuCard.get();
            List<Menu> menuItems = menuCard.getMenuItems();
            log.info("Found {} menu items for menu card {}", menuItems.size(), id);
            //return menuItems;
            return Collections.emptyList(); // replace with an empty list
        } else {
            log.error("Menu card {} not found", id);
            return null;
        }
    }


}
