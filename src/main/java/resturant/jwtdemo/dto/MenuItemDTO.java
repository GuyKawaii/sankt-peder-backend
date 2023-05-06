package resturant.jwtdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
//    private String price;
}
