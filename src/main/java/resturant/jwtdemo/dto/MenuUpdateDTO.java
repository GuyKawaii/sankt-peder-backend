package resturant.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import resturant.jwtdemo.entity.Menu;
import resturant.jwtdemo.entity.MenuItem;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDTO {
    @JsonProperty("menu")
    private Menu menu;
    @JsonProperty("menuItems")
    private List<MenuItem> menuItems;
}
