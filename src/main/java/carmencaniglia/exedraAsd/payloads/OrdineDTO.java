package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.StatoOrdine;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record OrdineDTO(@NotNull(message = "L'ID dell'utente è obbligatorio")
                        Long utenteId,

                        @PastOrPresent(message = "La data dell'ordine deve essere nel passato o nel presente")
                        Date dataOrdine,

                        @NotNull(message = "Lo stato dell'ordine è obbligatorio")
                        StatoOrdine statoOrdine) {
}
