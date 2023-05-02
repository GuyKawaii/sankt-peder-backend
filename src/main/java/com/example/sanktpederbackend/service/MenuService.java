package com.example.sanktpederbackend.service;

import com.example.sanktpederbackend.model.Foto;
import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Integer id) {
        return menuRepository.findById(id);
    }

    public ResponseEntity<Menu> putMenu(Menu menu, Integer menuId) {
        if (doesMenuExist(menuId)) {
            menu.setId(menuId);
            Menu updatedMenu = menuRepository.save(menu);
            return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Menu> deleteMenu(Integer menuId) {
        Optional<Menu> optionalMenu = menuRepository.findById(menuId);
        if (doesMenuExist(menuId)) {
            Menu deleteMenu = optionalMenu.get();
            menuRepository.deleteById(menuId);
            return new ResponseEntity<>(deleteMenu, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Menu> putMenus(List<Menu> menus) {
        for (Menu menu : menus) {
            if (doesMenuExist(menu)) menuRepository.save(menu);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Menu> postMenu(Menu menu) {
        if (doesMenuExist(menu)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Menu postedMenu = menuRepository.save(menu);
        return new ResponseEntity<>(postedMenu, HttpStatus.OK);
    }

    private boolean doesMenuExist(Menu menu) {
        Integer menuId = menu.getId();
        return doesMenuExist(menuId);
    }

    private boolean doesMenuExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && menuRepository.existsById(id);
    }
}
