package com.example.sanktpederbackend.config;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initData implements CommandLineRunner {
    // ### run tests ###
    private final boolean runTests = true;
    private final MenuService menuService;

    @Autowired
    public initData(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!runTests) return;

        frokostMenu();
    }

    private void frokostMenu() {
        Menu menu = new Menu();
        menu.setName("Frokost");
        menu.setDescription("Frokost menu");
        menu.setPrice("100");

        menuService.postMenu(menu);
    }
}
