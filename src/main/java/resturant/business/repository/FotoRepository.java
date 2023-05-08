package resturant.business.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import resturant.business.entity.Foto;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
}
