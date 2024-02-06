package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneDAO extends JpaRepository<PrenotazioneCorso, Long> {
}
