package com.example.sanktpederbackend.config;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.model.MenuCard;
import com.example.sanktpederbackend.service.MenuCardService;
import com.example.sanktpederbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initData implements CommandLineRunner {
    // ### run tests ###
    private final boolean runTests = true;
    private final MenuService menuService;
    private final MenuCardService menuCardService;

    @Autowired
    public initData(MenuService menuService, MenuCardService menuCardService) {
        this.menuService = menuService;
        this.menuCardService = menuCardService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!runTests) return;

        // Create and add items to the first MenuCard
        MenuCard menuCard1 = new MenuCard();
        menuCard1.setName("Frokost");

        Menu menu1 = new Menu();
        menu1.setName("Frokost menu");
        menu1.setDescription("A delicious menu for breakfast.");
        menu1.setPrice("100");
        menu1.setMenuCard(menuCard1);
        menuCard1.addMenuItem(menu1);

        Menu menu2 = new Menu();
        menu2.setName("Healthy breakfast");
        menu2.setDescription("A healthy breakfast to start your day.");
        menu2.setPrice("120");
        menu2.setMenuCard(menuCard1);
        menuCard1.addMenuItem(menu2);

        menuCardService.postMenuCard(menuCard1);

        // Create and add items to a second MenuCard
        MenuCard menuCard2 = new MenuCard();
        menuCard2.setName("Dinner");

        Menu menu3 = new Menu();
        menu3.setName("Steak");
        menu3.setDescription("A delicious steak cooked to perfection.");
        menu3.setPrice("250");
        menu3.setMenuCard(menuCard2);
        menuCard2.addMenuItem(menu3);

        Menu menu4 = new Menu();
        menu4.setName("Fish and chips");
        menu4.setDescription("Classic dish of battered fish and chips.");
        menu4.setPrice("120");
        menu4.setMenuCard(menuCard2);
        menuCard2.addMenuItem(menu4);

        menuCardService.postMenuCard(menuCard2);
    }
}
