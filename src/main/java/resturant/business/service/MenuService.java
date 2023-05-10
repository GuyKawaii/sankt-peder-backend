package resturant.business.service;


import resturant.business.dto.MenuItemDTO;
import resturant.business.dto.MenuUpdateDTO;
import resturant.business.entity.MenuItem;
import resturant.business.entity.Menu;
import resturant.business.repository.MenuItemRepository;
import resturant.business.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", id));
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = getMenuById(id);
        menu.setName(menuDetails.getName());
        menu.setMenuItems(menuDetails.getMenuItems());
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem", "id", id));
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) {
        MenuItem menuItem = getMenuItemById(id);
        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setMenus(menuItemDetails.getMenus());
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        MenuItem menuItem = getMenuItemById(id);
        menuItemRepository.delete(menuItem);
    }

    public List<MenuItem> getMenuItemsByMenuId(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));
        return menu.getMenuItems();
    }

    public Menu updateMenuAndItems(Long menuId, MenuUpdateDTO menuUpdateDTO) {
        // Find the existing Menu object based on the given menuId
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));

        // Update the name of the Menu object based on the given menuUpdateDTO
        menu.setName(menuUpdateDTO.getMenu().getName());

        // Create a new list of MenuItems based on the given menuUpdateDTO
        List<MenuItem> menuItems = new ArrayList<>();

        // Create a map of the existing MenuItem objects based on their IDs
        Map<Long, MenuItem> existingMenuItems = menu.getMenuItems().stream()
                .collect(Collectors.toMap(MenuItem::getId, menuItem -> menuItem));

        // Iterate through the list of MenuItemDTO objects in the given menuUpdateDTO
        for (MenuItemDTO menuItemDTO : menuUpdateDTO.getMenuItems()) {
            // Find the existing MenuItem object based on the given menuItemDTO's ID
            MenuItem menuItem = existingMenuItems.get(menuItemDTO.getId());

            if (menuItem == null) {
                // If the existing MenuItem object is not found, create a new MenuItem object
                menuItem = new MenuItem();
                // Set the name, description, and price of the new MenuItem object based on the given menuItemDTO
                menuItem.setName(menuItemDTO.getName());
                menuItem.setDescription(menuItemDTO.getDescription());
                menuItem.setPrice(menuItemDTO.getPrice());
                // Add the updated Menu object to the new MenuItem object's menus field
                menuItem.getMenus().add(menu);
                // Save the new MenuItem object to the repository
                menuItemRepository.save(menuItem);
            } else {
                // If the existing MenuItem object is found, update its name, description, and price based on the given menuItemDTO
                menuItem.setName(menuItemDTO.getName());
                menuItem.setDescription(menuItemDTO.getDescription());
                menuItem.setPrice(menuItemDTO.getPrice());
            }

            // Add the MenuItem object to the new list of MenuItems
            menuItems.add(menuItem);
        }

        // Set the Menu object's menuItems field to the new list of MenuItems
        menu.setMenuItems(menuItems);

        // Save the updated Menu object to the repository and return it
        return menuRepository.save(menu);
    }



}

