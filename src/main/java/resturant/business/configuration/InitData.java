package resturant.business.configuration;

import resturant.business.entity.Image;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.repository.ImageRepository;
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
    private ImageRepository imageRepository;

    private Image createOrGetFoto(String url, String imagePath, MenuItem menuItem) throws IOException {
        // Check if a Foto with the same URL already exists in the database
        Optional<Image> optionalFoto = imageRepository.findByUrl(url);

        if (optionalFoto.isPresent()) {
            // If it does, reuse it
            return optionalFoto.get();
        } else {
            // If not, create a new one
            Path path = Paths.get(imagePath);
            byte[] imageData = Files.readAllBytes(path);
            Image image = new Image();
            image.setUrl(url);
            image.setData(imageData);
            image.setMenuItem(menuItem);
            return imageRepository.save(image);
        }
    }


    public void createBasicLunchMenu() throws IOException {

        // Create menu items
        List<MenuItem> menuItems = new ArrayList<>();

        Image image1 = createOrGetFoto(
                "https://example.com/images/1",
                "src/main/resources/static/food_1.jpg",
                null);
        Image image2 = createOrGetFoto(
                "https://example.com/images/2",
                "src/main/resources/static/food_2.jpg",
                null);
        Image image3 = createOrGetFoto(
                "https://example.com/images/3",
                "src/main/resources/static/food_3.jpg",
                null);
        Image image4 = createOrGetFoto(
                "https://example.com/images/4",
                "src/main/resources/static/food_4.jpg",
                null);
        Image image5 = createOrGetFoto(
                "https://example.com/images/5",
                "src/main/resources/static/food_5.jpg",
                null);
        Image image6 = createOrGetFoto(
                "https://example.com/images/6",
                "src/main/resources/static/food_6.jpg",
                null);


        menuItems.add(new MenuItem(
                null,
                "Sankt Peders hjemmelavede karrysild m. æble og æg",
                "Herring in homemade curry dressing with eggs (house speciality)",
                new BigDecimal("95"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 12",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 13",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 14",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 15",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 16",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);
        image3.setMenuItem(menuItems.get(2));
        imageRepository.save(image3);
        image4.setMenuItem(menuItems.get(3));
        imageRepository.save(image4);
        image5.setMenuItem(menuItems.get(4));
        imageRepository.save(image5);
        image6.setMenuItem(menuItems.get(5));
        imageRepository.save(image6);

        // Create menu
        Menu menu = new Menu(null, "EARLY MENU", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createBasicDinnerMenu() throws IOException {
        // Create menu items
        List<MenuItem> menuItems = new ArrayList<>();

        Image image1 = createOrGetFoto(
                "https://example.com/images/karrysild.jpg",
                "src/main/resources/static/food_1.jpg",
                null);
        Image image2 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/food_2.jpg",
                null);
        Image image3 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/food_3.jpg",
                null);
        Image image4 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/food_4.jpg",
                null);
        Image image5 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/food_5.jpg",
                null);
        Image image6 = createOrGetFoto(
                "https://example.com/images/smoked-mackerel.jpg",
                "src/main/resources/static/food_6.jpg",
                null);


        menuItems.add(new MenuItem(
                null,
                "Sankt Peders hjemmelavede karrysild m. æble og æg",
                "Herring in homemade curry dressing with eggs (house speciality)",
                new BigDecimal("95"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 2",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 3",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 4",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 5",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme 6",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "LATER MENU", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }


    @Override
    public void run(String... args) throws Exception {
        // Create basic lunch menu
        createBasicLunchMenu();
//        createBasicDinnerMenu();
    }
}
