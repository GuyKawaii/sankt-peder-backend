package resturant.business.configuration;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import resturant.business.entity.Image;
import resturant.business.entity.Menu;
import resturant.business.entity.MenuItem;
import resturant.business.repository.ImageRepository;
import resturant.business.repository.MenuItemRepository;
import resturant.business.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
                "Ruths kryddersild fra Christiansø m. rødløg og kapers",
                "Ruth’s pickled herring from \"Christiansø\" with onions and capers",
                new BigDecimal("95"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Lun stegt sild i lage m. rødløg og kapers",
                "Fried herring in mild vinegar with onions and capers",
                new BigDecimal("95"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Æg og rejer med ørredrogn og mayo",
                "Egg with shrimps, mayo and trout roe",
                new BigDecimal("130"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "Røget ål m. lun røræg og purløg",
                "Smoked eel with scrambled egg and chives",
                new BigDecimal("145"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "”Snapsemad” kartoffel, æg, bacon, benfri sild og mayo",
                "Snapsemad” potatoes, egg, bacon, herring and mayo",
                new BigDecimal("110"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Pandestegt rødspættefilet m. remoulade",
                "Pan-fried filet of plaice with pickled mayo",
                new BigDecimal("140"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "\"Englekys\" rødspættefilet m. rejer, ørredrogn, asparges, mayo",
                "\"Angelkiss\" filet of plaice, shrimps, trout roe, asparagus, mayo",
                new BigDecimal("150"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Røget Fanø laks m. stuvet spinat",
                "Smoked salmon from Fanø with spinach stew",
                new BigDecimal("125"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "\"Sol over Sankt Peder\" Røget makrel, radiser, purløg, æggeblomme",
                "\"Sun over Sankt Peder\" smoked mackerel, radishes, chives, egg yolk",
                new BigDecimal("120"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "”Den Fromme” avocado på ristet rugbrød, rejer og creme fraiche",
                "”The Pious” Avocado on toasted rye bread with shimps and sour creme",
                new BigDecimal("125"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Kartoffelmad m. bacon, løg, purløg og mayo",
                "Potato with bacon, red onions, chives and mayo",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet hønsesalat (landkylling) m. bacon og asparges",
                "Homemade chicken mayo-salad with bacon and asparagus on toast",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Oksetatar med det hele",
                "Steak tatare with egg yolk, pickles, horseradish and capers",
                new BigDecimal("140"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Rørt Tatar a la sankt peder",
                "Mixed tatare a la sankt peder",
                new BigDecimal("165"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Pariserbøf Præstens(290gr)",
                "Fried steak tartare with egg yolk, horseradish and capers",
                new BigDecimal("165"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Alterdrengens (190gr)",
                "",
                new BigDecimal("145"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Lun flæskesteg m. rødkål og agurkesalat",
                "Warm roast pork with red cabbage and pickled cucumber",
                new BigDecimal("110"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Roastbeef m. lune bløde løg (spejlæg +10,-)",
                "Roastbeef with freshly fried onions (fried egg +10,-)",
                new BigDecimal("95"),
                image6));


        menuItems.add(new MenuItem(
                null,
                "Lun leverpostej m. bacon og rødbeder og agurkesalat",
                "Warm liver paste with bacon and pickled cucumber",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Landskinke m. spejlæg, tomater og purløg",
                "Ham with fried egg, tomatoes and chives",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Dyrelægens natmad m. saltkød, rødløg og hjemmelavet sky",
                "\"Vet's Delight\" liver paste with salt silverside, jelly stock and chives",
                new BigDecimal("95"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet rullepølse m. løg, sky og hjemmerørt sennep",
                "Homemade spiced meat roll with jelly stock, onions and mustard",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Økologisk Vesterhavsost fra Thiese på ristet rugbrød m. druer",
                "Organic cheese from Thiese in north jutland on toasted rye bread and grapes",
                new BigDecimal("95"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Gammelost på fedt m. sky, løg og mørk rum",
                "Old cheese with jelly stock, onions and dark rum",
                new BigDecimal("95"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Gorgonzola m. rødløg og æggeblomme",
                "Gorgonzola with onions and egg yolk",
                new BigDecimal("95"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Desserter",
                "Vi har altid hjemmelavet dessert- spørg tjeneren for dagens dessert fra ",
                new BigDecimal("80"),
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
        Menu menu = new Menu(null, "MENU 11.30 - 21.00", menuItems);

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
                "Mandag",
                "Stegt flæsk m. persillesovs",
                new BigDecimal("0"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "Tirsdag",
                "Hel rødspætte m. persillesovs",
                new BigDecimal("0"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Onsdag",
                "Tarteletter m. høns og asparges",
                new BigDecimal("0"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Torsdag",
                "Wienersnitzel mit alles",
                new BigDecimal("0"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "Fredag",
                "Ribeye m. sovs og kartofler\n",
                new BigDecimal("0"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "Lørdag",
                "Spørg tjeneren",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Søndag",
                "Frokostkort",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Ugens dessert",
                "Gl daws æblekage,-",
                new BigDecimal("0"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "MIDDAGSRET 18.00 - 21.00", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }
    public void createMenuOver15() throws IOException {
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
                "Ruths kryddersild fra Christiansø m. rødløg og kapers",
                "Ruth’s pickled herring from \"Christiansø\" with onions and capers",
                new BigDecimal("95"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Røget ål m. lun røræg og purløg",
                "Smoked eel with scrambled egg and chives",
                new BigDecimal("145"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Pandestegt rødspættefilet m. remoulade",
                "Pan-fried filet of plaice with pickled mayo",
                new BigDecimal("140"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "Røget færøsk laks m. urtecreme",
                "Smoked salmon from the Faroe Islands with spinach stew",
                new BigDecimal("125"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet hønsesalat m. bacon og asparges",
                "Homemade chicken mayo-salad with bacon and asparagus on toast",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Lun flæskesteg m. rødkål og agurkesalat",
                "Warm roast pork with red cabbage and pickled cucumber",
                new BigDecimal("110"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Lun leverpostej m. bacon og rødbeder og agurkesalat",
                "Warm liver paste with bacon and pickled cucumber",
                new BigDecimal("100"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Ostefad med kiks og druer",
                "Assorted cheeses with crackers and grapes",
                new BigDecimal("125"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet dessert",
                "Homemade dessert",
                new BigDecimal("80"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "MENUKORT 10-50 PERSONER", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createJuleMenu() throws IOException {
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
                "Hjemmelavet karrysild & Christians Ø sild",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "Pandestegt rødspættefilet m. hjemmerørt remoulade",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet hønsesalat m. asparges og bacon",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Flæskesteg m. hjemmelavet rødkål og surt",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "Julemedister med hjemmelavet rødkål",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "Hjemmelavet ris a la mande",
                "JULE MENU TIL KR. 475,- PR. PERSON - Søndag til onsdag og KR. 445,-",
                new BigDecimal("0"),
                image6));

        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "JULEMENU 2023", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createSelskabsmenu() throws IOException {
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
                "Røget Fanø laks m. dildcreme",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "Rejecocktail",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Oksetyndsteg m. årstidens grønt og hjemmelavet bearnaise",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Kalvefilet m. årstidens grønt og rødvinssovs",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "Gl. Dags æblekage",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "Dagens dessert",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Ostetallerken m. kiks og druer (+25)",
                "2 retter kr. 325,- 3 retter kr. 375,-",
                new BigDecimal("0"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "Selskabsmenu", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createDrinkMenu() throws IOException {
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
                "Aperol spritz",
                "",
                new BigDecimal("85"),
                image1));

        menuItems.add(new MenuItem(
                null,
                "Alm. Gin, Rom,Vodka,Whisky 3 cl",
                "",
                new BigDecimal("55"),
                image2));

        menuItems.add(new MenuItem(
                null,
                "Lux Gin, Rom, Whisky 3 cl",
                "",
                new BigDecimal("70"),
                image3));

        menuItems.add(new MenuItem(
                null,
                "Dark & Stormy / Mojito /Moscow Mule",
                "",
                new BigDecimal("100"),
                image4));

        menuItems.add(new MenuItem(
                null,
                "White/Black Russian",
                "",
                new BigDecimal("110"),
                image5));

        menuItems.add(new MenuItem(
                null,
                "Tuborg Grøn",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Tuborg Classic",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Jacoben Brown Ale",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Jacobsen Yakima IPA",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Carlsberg 1883",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Grimbergen Blonde",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Grimbergen Double",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "1664 Blanc",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Nørrebro Bombay IPA",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Nørrebro Ravnsborg Rød",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Anarkist New england IPA",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Anarkist Bloody Weisse",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Praga Pilsner",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Lagonitas IPA",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Schiøtz Mumme",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Div. Sæson øl",
                "",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "DRIKKEVARER", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createSnapMenu() throws IOException {
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
                "Linie Akvavit",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Nord Guld",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "O.P. Andersen",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Harald Jensen",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Jubilæum",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Rød Ålborg",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Bornholmer",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Brøndum",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Porse Snaps",
                "",
                new BigDecimal("0"),
                image6));

        menuItems.add(new MenuItem(
                null,
                "Gl. Opland",
                "",
                new BigDecimal("0"),
                image6));

        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "SNAPS", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createSodavandMenu() throws IOException {
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
                "Haarslev Gaard Hyldeblomst, Æblemost",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Coca Cola, Light, Zero, Max",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Faxe Kondi",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Sweppes Lemon",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Sprite",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Squash",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Dansk Vand Citron",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Dansk Vand",
                "",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "SODAVAND", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createVarmeDrikke() throws IOException {
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
                "Filter kaffe - hjemmekværnet øko m. varm mælk",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Irish Coffee",
                "",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Te",
                "",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "VARME DRIKKE", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createHvidVinMenu() throws IOException {
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
                "Husets hvidvin, Nespoli Chardonnay - Italien",
                "Glas 59,- / Flaske 255,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Sauvignon Blanc, Soho Winery - New Zealand",
                "Glas 70,- / Flaske 325,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Riesling 1648, Thanisch, Mosel - Tyskland",
                "Flaske 379,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Chablis, Chanson Pere et Fils - Frankrig",
                "Flaske 465,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Sancerre, Langlois - Frankrig",
                "Flaske 389,-",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "HVIDVIN", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }
    public void createRoseVin() throws IOException {
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
                "Fontanet - Les Terrasses - Frankrig",
                "Glas 59,- / Flaske 255,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Ch. Roubine - Cru Classé - Frankrig",
                "Flaske 369,-",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "ROSEVIN", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createRødVin() throws IOException {
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
                "Husets rødvin, Nespoli Sangiovese/Merlot - Italien",
                "Glas 59,- / Flaske 255,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Pinot Noir, La Cour des Dames - Frankrig",
                "Glas 68,- / Flaske 295,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Chianti, Torre a Cona, Toscana - Italien",
                "Flaske 335,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Zinfandel, \"Sin Zin\" Alexander Valley, Californien - USA",
                "Flaske 375,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "St. Emilion Grand Cru - La Croix, Bordeaux - Frankrig",
                "Flaske 445,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Chateauneuf, Bosquet des Papes - Rhone - Frankrig",
                "Flaske 625,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Barolo \"Lecinquevigne\" Damilano - Piemonte - Italien",
                "Flaske 695,-",
                new BigDecimal("0"),
                image6));
        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "RØDVIN", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createPortVin() throws IOException {
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
                "10 års - Quinta de la Rosa",
                "Glas 75,- / Flaske 750,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Husets portvin - LOT 601 (det gamle fad)",
                "Glas 50,- / Flaske 500,-",
                new BigDecimal("0"),
                image6));

        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "PORTVIN", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    public void createChampagneMenu() throws IOException {
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
                "Sparkling vin",
                "Cava Brut - Pinord - Spanien, Glas 69,- / Flaske 400,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Rosé Cava Brut - Pinord - Spanien",
                "Flaske 500,-",
                new BigDecimal("0"),
                image6));
        menuItems.add(new MenuItem(
                null,
                "Champagne Brut",
                "Flaske 675,-",
                new BigDecimal("0"),
                image6));


        // Save menu items to repository
        menuItemRepository.saveAll(menuItems);

        // Update menu item photos
        image1.setMenuItem(menuItems.get(0));
        imageRepository.save(image1);
        image2.setMenuItem(menuItems.get(1));
        imageRepository.save(image2);

        // Create menu
        Menu menu = new Menu(null, "CHAMPAGNE OG SPARKLING", menuItems);

        // Save menu to repository
        menuRepository.save(menu);
    }

    @Override
    public void run(String... args) throws Exception {

         //Create basic lunch menu
//       createBasicLunchMenu();
//       createBasicDinnerMenu();
//       createMenuOver15();
//       createJuleMenu();
//       createSelskabsmenu();
//       createDrinkMenu();
//       createSnapMenu();
//       createSodavandMenu();
//       createVarmeDrikke();
//       createHvidVinMenu();
//       createRoseVin();
//       createRødVin();
//       createPortVin();
//       createChampagneMenu();
    }
}
