package carmencaniglia.exedraAsd.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "prenotazioni_corsi")
public class PrenotazioneCorso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dataOra;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
}
