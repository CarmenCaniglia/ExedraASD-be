package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.DettaglioOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DettaglioDAO extends JpaRepository<DettaglioOrdine, Long> {
}
