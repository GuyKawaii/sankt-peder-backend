package resturant.business.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    @JsonProperty("id")
    private int id;

    @Column(name = "url")
    @JsonProperty("url")
    private String url;

    @Column(name = "data", columnDefinition="MEDIUMBLOB")
    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "menu_item_id")
    @JsonBackReference
    private MenuItem menuItem;
}


