package carmencaniglia.exedraAsd.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DettaglioDTO(@NotNull(message = "L'ID dell'ordine è obbligatorio")
                           Long ordineId,

                           @NotNull(message = "L'ID del prodotto è obbligatorio")
                           Long prodottoId,

                           @Min(value = 1, message = "La quantità deve essere almeno 1")
                           int quantità,

                           @Min(value = 0, message = "Il prezzo non può essere negativo")
                           double prezzo) {
}
