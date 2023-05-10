package resturant.business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.service.MenuItemService;

@RestController
@RequestMapping("/menuItem")
@CrossOrigin("*")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // Get a menu item by its ID
    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuById(@PathVariable(value = "menuItemId") Long menuItemId) {
        MenuItem menuItem = menuItemService.findById(menuItemId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }



}
