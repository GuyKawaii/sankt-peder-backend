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

    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public Optional<MenuItem> getMenuItem(Long id) {
        return menuItemRepository.findById(id);
    }

    public ResponseEntity<MenuItem> putMenuItem(MenuItem menuItem, Long id) {
        if(doesMenuItemExist(id)) {
            menuItem.setId(id);
            MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
            return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteMenuItem(Long id) {
        if(doesMenuItemExist(id)) {
            menuItemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> putMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            if (doesMenuItemExist(menuItem.getId())) menuItemRepository.save(menuItem);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<MenuItem> postMenu(MenuItem menuItem) {
        MenuItem postedMenuItem = menuItemRepository.save(menuItem);
        return new ResponseEntity<>(postedMenuItem, HttpStatus.CREATED);
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
