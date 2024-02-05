package carmencaniglia.exedraAsd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dettagli_ordine")
public class DettaglioOrdine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    private int quantità;
    private double prezzo;
}
