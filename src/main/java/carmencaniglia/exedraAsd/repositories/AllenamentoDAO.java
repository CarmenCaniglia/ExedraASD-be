package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.SchedaAllenamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllenamentoDAO extends JpaRepository<SchedaAllenamento, Long> {
}
