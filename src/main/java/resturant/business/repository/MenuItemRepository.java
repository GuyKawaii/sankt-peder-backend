package resturant.business.repository;


import resturant.business.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT mi FROM MenuItem mi JOIN mi.menus m WHERE m.id = :menuId")
    List<MenuItem> findMenuItemsByMenuId(@Param("menuId") Long menuId);
}
