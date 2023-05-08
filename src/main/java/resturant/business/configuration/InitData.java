package resturant.business.configuration;

import resturant.business.entity.Foto;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.repository.FotoRepository;
import resturant.business.repository.MenuItemRepository;
import resturant.business.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private FotoRepository fotoRepository;

    private void createAndSaveFoto(String name, String description, String url, String imagePath) throws IOException {
        Foto foto = new Foto();
        foto.setName(name);
        foto.setDescription(description);
        foto.setUrl(url);

        Path path = Paths.get(imagePath);
        byte[] imageData = Files.readAllBytes(path);
        foto.setData(imageData);

        fotoRepository.save(foto);
    }

    public void createBasicLunchMenu() {

        // Create menu items
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem(
                1L,
                "Sankt Peders hjemmelavede karrysild m. æble og æg",
                "Herring in homemade curry dressing with eggs (house speciality)\n",
                new BigDecimal("95"), null));

        menuItems.add(new MenuItem(
                2L,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"), null));

        menuItems.add(new MenuItem(
                3L,
                "Lun leverpostej m. bacon og rødbeder og agurkesalat",
                "Warm liver paste with bacon and pickled cucumber",
                new BigDecimal("100"), null));

        menuItems.add(new MenuItem(
                4L,
                "Ruths kryddersild fra Christiansø m. rødløg og kapers",
                "Ruth’s pickled herring from 'Christiansø' with onions and capers",
                new BigDecimal("100"), null));

        menuItems.add(new MenuItem(
                5L,
                "”Den Fromme” avocado på ristet rugbrød, rejer og creme fraiche",
                "”The Pious” Avocado on toasted rye bread with shimps and sour creme",
                new BigDecimal("125"), null));

        menuItems.add(new MenuItem(
                6L,
                "Landskinke m. spejlæg, tomater og purløg",
                "Ham with fried egg, tomatoes and chives",
                new BigDecimal("100"), null));

        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Create menu
        Menu menu = new Menu(1L, "MENU 11.30 - 21.00", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }


    @Override
    public void run(String... args) throws Exception {
        createAndSaveFoto("My Image", "A sample image", "https://example.com/myimage.jpg", "src/main/resources/static/gris.jpg");

        // Create basic lunch menu
        createBasicLunchMenu();
    }
}
