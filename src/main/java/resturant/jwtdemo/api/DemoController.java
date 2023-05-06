package resturant.jwtdemo.api;

import resturant.jwtdemo.service.MenuItemService;
import resturant.jwtdemo.service.MenuService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Getter
class InfoResponse {
    String info;
    public InfoResponse(String info) {
        this.info = info;
    }
}

@RestController
@RequestMapping("/api/demo")
@CrossOrigin
public class DemoController {

    private final MenuService menuService;
    private final MenuItemService menuItemService;

    @Autowired
    public DemoController(MenuService menuService, MenuItemService menuItemService) {
        this.menuService = menuService;
        this.menuItemService = menuItemService;
    }


    @GetMapping("/anonymous")
    public ResponseEntity<InfoResponse> getAnonymousInfo() {
        return new ResponseEntity<InfoResponse>(new InfoResponse("Hi Non Authenticated User"), HttpStatus.OK);
    }

    @GetMapping("/authenticated")
    public ResponseEntity<InfoResponse> getAuthenticatedUserInfo() {
        return new ResponseEntity<InfoResponse>(new InfoResponse("Hi Authenticated"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/user-admin")
    public ResponseEntity<InfoResponse> getUserAdminInfo() {
        return new ResponseEntity<InfoResponse>(new InfoResponse("Hi User or Admin"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<InfoResponse> getAdminInfo() {
        return new ResponseEntity<InfoResponse>(new InfoResponse("Hello Admin"), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public ResponseEntity<InfoResponse> getUserInfo() {
        return new ResponseEntity<InfoResponse>(new InfoResponse("Hello User"), HttpStatus.OK);
    }

    @GetMapping("/user-fromtoken")
    public ResponseEntity<InfoResponse> getUserInfo(Principal p) {
        String info = "Current user is " + p.getName();
        return new ResponseEntity<InfoResponse>(new InfoResponse(info), HttpStatus.OK);
    }

}