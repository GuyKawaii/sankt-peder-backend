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
import java.util.List;
import java.util.Optional;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private FotoRepository fotoRepository;

    private Foto createOrGetFoto(String url, String imagePath, MenuItem menuItem) throws IOException {
        // Check if a Foto with the same URL already exists in the database
        Optional<Foto> optionalFoto = fotoRepository.findByUrl(url);

        if (optionalFoto.isPresent()) {
            // If it does, reuse it
            return optionalFoto.get();
        } else {
            // If not, create a new one
            Path path = Paths.get(imagePath);
            byte[] imageData = Files.readAllBytes(path);
            Foto foto = new Foto();
            foto.setUrl(url);
            foto.setData(imageData);
            foto.setMenuItem(menuItem);
            return fotoRepository.save(foto);
        }
    }


    public void createBasicLunchMenu() throws IOException {

        // Create menu items
        List<MenuItem> menuItems = new ArrayList<>();

        Foto foto1 = createOrGetFoto(
                "https://example.com/images/karrysild.jpg",
                "src/main/resources/static/gris.jpg",
                null);
        menuItems.add(new MenuItem(
                null,
                "Sankt Peders hjemmelavede karrysild m. æble og æg",
                "Herring in homemade curry dressing with eggs (house speciality)\n",
                new BigDecimal("95"),
                foto1));

        Foto foto2 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/Screenshot_1.png",
                null);
        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                foto2));

        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        foto1.setMenuItem(menuItems.get(0));
        fotoRepository.save(foto1);
        foto2.setMenuItem(menuItems.get(1));
        fotoRepository.save(foto2);

        // Create menu
        Menu menu = new Menu(null, "MENU 11.30 - 21.00", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }


    @Override
    public void run(String... args) throws Exception {
        // Create basic lunch menu
        createBasicLunchMenu();
    }
}
