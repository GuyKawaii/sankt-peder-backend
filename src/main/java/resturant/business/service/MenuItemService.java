package resturant.business.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItem(Long id) {
        return menuItemRepository.findById(id);
    }
    public ResponseEntity<MenuItem> putMenuItem(MenuItem menuItem, Long menuItemId) {
        if(doesMenuItemExist(menuItemId)) {
            menuItem.setId(menuItemId);
            MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
            return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<MenuItem> deleteMenuItem(Long menuItemId) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        if(doesMenuItemExist(menuItemId)) {
            MenuItem deleteMenuItem = optionalMenuItem.get();
            menuItemRepository.deleteById(menuItemId);
            return new ResponseEntity<>(deleteMenuItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<MenuItem> putMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            if (doesMenuItemExist(menuItem)) menuItemRepository.save(menuItem);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<MenuItem> postMenu(MenuItem menuItem) {
        if (doesMenuItemExist(menuItem)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        MenuItem postedMenuItem = menuItemRepository.save(menuItem);
        return new ResponseEntity<>(postedMenuItem, HttpStatus.OK);
    }

    private boolean doesMenuItemExist(MenuItem menuItem) {
        Long menuItemId = menuItem.getId();
        return doesMenuItemExist(menuItemId);
    }

    private boolean doesMenuItemExist(Long id) {
        boolean isIdSet = id != null;
        return isIdSet && menuItemRepository.existsById(id);
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }


    public List<MenuItem> findAllByMenuId(Long name) {
        return menuItemRepository.findMenuItemsByMenuId(name);
    }
}
