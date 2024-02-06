package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbbonamentoDAO extends JpaRepository<Abbonamento, Long> {
}
