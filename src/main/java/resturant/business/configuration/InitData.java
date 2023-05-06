package resturant.business.configuration;

import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.repository.MenuItemRepository;
import resturant.business.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public void createBasicLunchMenu() {

        // Create menu items
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setName("Cheeseburger");
        menuItem1.setDescription("Juicy beef patty with melted cheese on a soft bun");
        menuItem1.setPrice(new BigDecimal("9.99"));
//        menuItem1.setPrice("3.99");

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("Caesar Salad");
        menuItem2.setDescription("Fresh romaine lettuce with croutons and parmesan cheese");
        menuItem2.setPrice(new BigDecimal("6.99"));
//        menuItem2.setPrice("3.99");

        MenuItem menuItem3 = new MenuItem();
        menuItem3.setName("French Fries");
        menuItem3.setDescription("Crispy potato fries lightly salted");
        menuItem3.setPrice(new BigDecimal("3.99"));
//        menuItem3.setPrice("3.99");

        // Save menu items to repository
        menuItemRepository.saveAll(Arrays.asList(menuItem1, menuItem2, menuItem3));

        // Create menu
        Menu menu = new Menu();
        menu.setName("Lunch");
        menu.setMenuItems(Arrays.asList(menuItem1, menuItem2, menuItem3));

        // Save menu to repository
        menuRepository.save(menu);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create basic lunch menu
        createBasicLunchMenu();
    }
}
