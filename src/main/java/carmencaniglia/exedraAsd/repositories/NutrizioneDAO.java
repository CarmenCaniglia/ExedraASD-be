package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.SchedaNutrizionale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrizioneDAO extends JpaRepository<SchedaNutrizionale, Long> {
}
