package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.Giorno;
import jakarta.validation.constraints.*;

public record CorsoDTO(
        @NotNull(message = "Il giorno è obbligatorio")
        Giorno giorno,
        @NotBlank(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 50, message = "Il nome deve avere tra 3 e 50 caratteri")
        String nome,

        @NotBlank(message = "La descrizione è obbligatoria")
        @Size(min = 10, max = 255, message = "La descrizione deve avere tra 10 e 255 caratteri")
        String descrizione,

        @NotBlank(message = "L'orario è obbligatorio")
        String orario,

        @Min(value = 3, message = "Il numero di partecipanti deve essere almeno 3")
        @Max(value = 20, message = "Il numero massimo di partecipanti non può superare 20")
        int maxPartecipanti
) {
}
