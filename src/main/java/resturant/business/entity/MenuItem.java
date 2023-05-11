package resturant.business.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToMany(mappedBy = "menuItems")
    @JsonBackReference
    private Set<Menu> menus = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
//    @JsonBackReference
    private Image image;

    public MenuItem(Long id, String name, String description, BigDecimal price, Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
