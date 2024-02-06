package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteDAO extends JpaRepository<Utente, Long> {

    Optional<Utente> findByEmail (String email);

}
