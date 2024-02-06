package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoDAO extends JpaRepository<Corso, Long> {
}
