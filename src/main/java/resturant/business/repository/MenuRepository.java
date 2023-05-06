package resturant.business.repository;

import resturant.business.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Query to find a menu by name
    Menu findByName(String name);
}
