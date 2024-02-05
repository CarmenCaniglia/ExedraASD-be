package carmencaniglia.exedraAsd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "schede_allenamento")
public class SchedaAllenamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    private String titolo;
    private String descrizione;
}
