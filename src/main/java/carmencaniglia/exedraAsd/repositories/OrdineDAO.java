package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineDAO extends JpaRepository<Ordine,Long> {
}
