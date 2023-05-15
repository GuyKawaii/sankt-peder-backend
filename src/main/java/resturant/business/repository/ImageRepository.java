package resturant.business.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import resturant.business.entity.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByUrl(String url);
}
