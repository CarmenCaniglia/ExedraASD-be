package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private int durata;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
}
