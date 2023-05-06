package resturant.business.service;

import resturant.business.entity.MenuItem;
import resturant.business.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteById(Long id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }


    public List<MenuItem> findAllByMenuId(Long name) {
        return menuItemRepository.findMenuItemsByMenuId(name);
    }
}
