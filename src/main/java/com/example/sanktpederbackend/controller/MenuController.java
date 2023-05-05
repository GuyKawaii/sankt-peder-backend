package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.model.MenuCard;
import com.example.sanktpederbackend.service.MenuCardService;
import com.example.sanktpederbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1")
public class MenuController {

    private final MenuService menuService;
    private final MenuCardService menuCardService;

    @Autowired
    public MenuController(MenuService menuService, MenuCardService menuCardService) {
        this.menuService = menuService;
        this.menuCardService = menuCardService;
    }

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Integer id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        if (menu.isPresent()) {
            return ResponseEntity.ok(menu.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/menuitems/{menuCardId}")
    public List<Menu> getMenuItemsByMenuCardId(@PathVariable Integer menuCardId) {
        Optional<MenuCard> menuCard = menuCardService.getMenuCardById(menuCardId);
        if (menuCard.isPresent()) {
            return menuCard.get().getMenuItems();
        } else {
            return new ArrayList<>();
        }
    }

    @PostMapping("/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Menu> postMenu(@RequestBody Menu menu) {
        return menuService.postMenu(menu);
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<Menu> putItem(@PathVariable Integer id, @RequestBody Menu menu) {
        return menuService.putMenu(menu, id);
    }

    // put multiple items
    @PutMapping("/menu")
    public ResponseEntity<Menu> putItems(@RequestBody List<Menu> menus) {
        return menuService.putMenus(menus);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Menu> deleteFoto(@PathVariable Integer id) {
        return menuService.deleteMenu(id);
    }
}
