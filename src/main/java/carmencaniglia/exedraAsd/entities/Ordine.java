package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.StatoOrdine;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private Date dataOrdine;

    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    @OneToMany(mappedBy = "ordine")
    private List<DettaglioOrdine> dettagliOrdine;
}
