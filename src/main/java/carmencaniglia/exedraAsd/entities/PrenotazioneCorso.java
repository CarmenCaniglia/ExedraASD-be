package carmencaniglia.exedraAsd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "prenotazioni_corsi")
@Getter
@Setter
public class PrenotazioneCorso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dataOra;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
}
