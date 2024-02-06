package carmencaniglia.exedraAsd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schede_nutrizionali")
@Getter
@Setter
public class SchedaNutrizionale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private String titolo;
    private String descrizione;
}
