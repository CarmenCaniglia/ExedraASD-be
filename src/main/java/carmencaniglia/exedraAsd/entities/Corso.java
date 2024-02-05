package carmencaniglia.exedraAsd.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "corsi")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descrizione;
    private String orario;
    private int maxPartecipanti;

    @OneToMany(mappedBy = "corso")
    List<PrenotazioneCorso> prenotazioni;
}
