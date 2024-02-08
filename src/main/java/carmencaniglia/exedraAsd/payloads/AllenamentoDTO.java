package carmencaniglia.exedraAsd.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AllenamentoDTO(@NotNull(message = "L'ID utente non può essere nullo")
                             long utenteId,
                             @NotEmpty(message = "Il titolo non può essere vuoto")
                             @Size(max = 40, message = "Il titolo può contenere al massimo 40 caratteri")
                             String titolo,
                             @NotEmpty(message = "La descrizione non può essere vuota")
                             @Size(max = 255, message = "La descrizione può contenere al massimo 255 caratteri")
                             String descrizione) {
}
