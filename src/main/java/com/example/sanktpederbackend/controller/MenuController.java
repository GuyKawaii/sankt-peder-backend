package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.model.Foto;
import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }
    @GetMapping("/menus/{id}")
    public Menu getMenusById(@PathVariable Integer id) {
        return menuService.getMenuById(id).orElseThrow(()-> new RuntimeException("Menus with id: " + id + " not found"));
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
