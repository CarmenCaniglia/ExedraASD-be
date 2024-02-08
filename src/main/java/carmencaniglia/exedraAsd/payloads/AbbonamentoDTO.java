package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AbbonamentoDTO(@NotNull(message = "Il tipo di abbonamento è obbligatorio")
                             TipoAbbonamento tipoAbbonamento,

                             @Min(value = 0, message = "Il prezzo deve essere maggiore o uguale a 0")
                              double prezzo,

                             @Min(value = 1, message = "La durata deve essere di almeno 1 mese")
                              int durata,

                             @NotBlank(message = "La descrizione è obbligatoria")
                              @Size(max = 255, message = "La descrizione non può superare i 255 caratteri")
                              String descrizione,

                             @NotNull(message = "L'ID dell'utente è obbligatorio")
                              Long utenteId) {
}
