package resturant.business.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class Foto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foto_id")
    @JsonProperty("id")
    private int id;

    @Column(name = "url")
    @JsonProperty("url")
    private String url;

    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    public Foto() {
    }

    public Foto(int id, String url, byte[] data) {
        this.id = id;

        this.url = url;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
