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
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", menuId));

        menu.setName(menuUpdateDTO.getMenu().getName());

        List<MenuItem> menuItems = new ArrayList<>();

        Map<Long, MenuItem> existingMenuItems = menu.getMenuItems().stream()
                .collect(Collectors.toMap(MenuItem::getId, menuItem -> menuItem));

        for (MenuItemDTO menuItemDTO : menuUpdateDTO.getMenuItems()) {
            MenuItem menuItem = existingMenuItems.get(menuItemDTO.getId());

            if (menuItem == null) {
                menuItem = new MenuItem();
                menuItem.setName(menuItemDTO.getName());
                menuItem.setDescription(menuItemDTO.getDescription());
                menuItem.setPrice(menuItemDTO.getPrice());
                menuItem.getMenus().add(menu);
                menuItemRepository.save(menuItem);
            } else {
                menuItem.setName(menuItemDTO.getName());
                menuItem.setDescription(menuItemDTO.getDescription());
                menuItem.setPrice(menuItemDTO.getPrice());
            }

            menuItems.add(menuItem);
        }

        menu.setMenuItems(menuItems);

        return menuRepository.save(menu);
    }



}

