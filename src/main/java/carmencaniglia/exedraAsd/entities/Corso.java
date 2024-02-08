package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.Giorno;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "corsi")
@Getter
@Setter
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Giorno giorno;
    private String nome;
    private String descrizione;
    private String orario;
    private int maxPartecipanti;

    @OneToMany(mappedBy = "corso")
    List<PrenotazioneCorso> prenotazioni;
}
