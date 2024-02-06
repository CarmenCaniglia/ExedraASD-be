package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoDAO extends JpaRepository<Prodotto, Long> {
}
