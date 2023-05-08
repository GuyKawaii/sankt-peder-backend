package resturant.business.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import resturant.business.entity.Foto;

import java.util.Optional;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
    Optional<Foto> findByUrl(String url);
}
