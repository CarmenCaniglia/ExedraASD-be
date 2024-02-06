package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String avatar;

    @OneToMany(mappedBy = "utente")
    private List<Abbonamento> abbonamenti;

    @OneToMany(mappedBy = "utente")
    private List<PrenotazioneCorso> prenotazioniCorsi;

    @OneToMany(mappedBy = "utente")
    private List<SchedaAllenamento> schedeAllenamento;

    @OneToMany(mappedBy = "utente")
    private List<SchedaNutrizionale> schedeNutrizionali;

    @OneToMany(mappedBy = "utente")
    private List<Ordine> ordini;
}
