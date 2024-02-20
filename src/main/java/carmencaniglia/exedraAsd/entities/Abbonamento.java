package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
@Getter
@Setter
public class Abbonamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;
    private double prezzo;
    //private int durata;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String descrizione;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public void aggiornaDataFine() {
        if(dataInizio == null || tipoAbbonamento == null) {
            throw new IllegalStateException("Data di inizio e tipo di abbonamento devono essere impostati.");
        }

        switch (tipoAbbonamento) {
            case MENSILE:
                dataFine = dataInizio.plusMonths(1);
                break;
            case TRIMESTRALE:
                dataFine = dataInizio.plusMonths(3);
                break;
            case ANNUALE:
                dataFine = dataInizio.plusYears(1);
                break;
        }
    }
}
