package resturant.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import resturant.business.entity.Menu;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuUpdateDTO {
    @JsonProperty("menu")
    private Menu menu;
    @JsonProperty("menuItems")
    private List<MenuItemDTO> menuItems;
}
