package carmencaniglia.exedraAsd.repositories;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbbonamentoDAO extends JpaRepository<Abbonamento, Long> {
    @Query("SELECT a FROM Abbonamento a WHERE a.utente.id = :utenteId AND a.dataFine >= CURRENT_DATE")
    List<Abbonamento>findAbbonamentiAttiviByUtenteId(@Param("utenteId") long utenteId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Abbonamento a WHERE a.utente.id = :utenteId")
    void deleteByUtenteId(@Param("utenteId") Long utenteId);

    @Query("SELECT a FROM Abbonamento a WHERE a.utente.id = :utenteId AND a.tipoAbbonamento = :tipo")
    List<Abbonamento> findAbbonamentiByUtenteIdAndTipo(@Param("utenteId") long utenteId, @Param("tipo") TipoAbbonamento tipoAbbonamento);
}
