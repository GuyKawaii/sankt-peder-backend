package resturant.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FotoDetails {
    private byte[] data;
    private String menuItemName;
    private String menuItemDescription;
}