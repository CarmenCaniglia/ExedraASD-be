package carmencaniglia.exedraAsd.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NutrizioneDTO(@NotNull(message = "L'ID dell'utente non può essere nullo")
                            long utenteId,
                            @NotEmpty(message = "Il titolo non può essere vuoto")
                            @Size(max = 50, message = "Il titolo deve essere lungo al massimo 50 caratteri")
                            String titolo,
                            @NotEmpty(message = "La descrizione non può essere vuota")
                            @Size(max = 255, message = "La descrizione deve essere lunga al massimo 255 caratteri")
                            String descrizione) {
}
