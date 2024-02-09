package carmencaniglia.exedraAsd.entities;

import carmencaniglia.exedraAsd.enums.StatoOrdine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordini")
@Getter
@Setter
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;

    private Date dataOrdine;

    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    @OneToMany(mappedBy = "ordine")
    private List<DettaglioOrdine> dettagliOrdine;
}
