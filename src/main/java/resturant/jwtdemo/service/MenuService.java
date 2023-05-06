package resturant.jwtdemo.service;


import org.springframework.beans.BeanUtils;
import resturant.jwtdemo.dto.MenuItemDTO;
import resturant.jwtdemo.dto.MenuUpdateDTO;
import resturant.jwtdemo.entity.MenuItem;
import resturant.jwtdemo.entity.Menu;
import resturant.jwtdemo.repository.MenuItemRepository;
import resturant.jwtdemo.repository.MenuRepository;
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

        for (MenuItem menuItemDTO : menuUpdateDTO.getMenuItems()) {
            MenuItem menuItem = existingMenuItems.get(menuItemDTO.getId());

            if (menuItem == null) {
                menuItem = menuItemDTO;
                menuItemRepository.save(menuItem);
            }

            menuItems.add(menuItem);
        }

        menu.setMenuItems(menuItems);

        return menuRepository.save(menu);
    }

}

