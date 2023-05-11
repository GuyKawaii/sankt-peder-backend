package resturant.business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.service.MenuItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menuItem")
@CrossOrigin("*")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/menuItems")
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    @PostMapping("/postMenuItem")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MenuItem> postMenu(@RequestBody MenuItem menuItem) {
        MenuItem savedMenuItem = menuItemService.save(menuItem);
        return new ResponseEntity<>(savedMenuItem, HttpStatus.CREATED);
    }

    @PutMapping("/menuItems/{id}")
    public ResponseEntity<MenuItem> putItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuItemService.putMenuItem(menuItem, id);
    }

    // put multiple items
    @PutMapping("/updateMenuItems")
    public ResponseEntity<?> putItems(@RequestBody List<MenuItem> menuItems) {
        return menuItemService.putMenuItems(menuItems);
    }

    @DeleteMapping("/deleteMenuItem/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        return menuItemService.deleteMenuItem(id);
    }

    // Get a menu item by its ID
    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuById(@PathVariable(value = "menuItemId") Long menuItemId) {
        MenuItem menuItem = menuItemService.findById(menuItemId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }



}
