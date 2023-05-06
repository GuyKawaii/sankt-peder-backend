package resturant.business.api;

import org.springframework.web.bind.annotation.*;
import resturant.business.dto.MenuItemDTO;
import resturant.business.dto.MenuUpdateDTO;
import resturant.business.entity.MenuItem;
import resturant.business.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import resturant.business.entity.Menu;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Create a menu
    @PostMapping
    public ResponseEntity<resturant.business.entity.Menu> createMenu(@RequestBody Menu menu) {
        Menu createdMenu = menuService.createMenu(menu);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    // Get a menu by its ID
    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable(value = "menuId") Long menuId) {
        Menu menu = menuService.getMenuById(menuId);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    // Update a menu
    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "menuId") Long menuId, @RequestBody Menu menuDetails) {
        Menu updatedMenu = menuService.updateMenu(menuId, menuDetails);
        return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
    }

    // Delete a menu
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable(value = "menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get menu items for a menu by its ID
    @GetMapping("/{menuId}/items")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByMenuId(@PathVariable(value = "menuId") Long menuId) {
        List<MenuItem> menuItems = menuService.getMenuItemsByMenuId(menuId);
        List<MenuItemDTO> menuItemDTOs = menuItems.stream()
                .map(item -> new MenuItemDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(menuItemDTOs, HttpStatus.OK);
    }


    @PutMapping("/{menuId}/updateMenuAndItems")
    public ResponseEntity<Menu> updateMenuAndItems(@PathVariable(value = "menuId") Long menuId, @RequestBody MenuUpdateDTO menuUpdateDTO) {
        Menu updatedMenu = menuService.updateMenuAndItems(menuId, menuUpdateDTO);
        return new ResponseEntity<>(updatedMenu, HttpStatus.OK);
    }



}

