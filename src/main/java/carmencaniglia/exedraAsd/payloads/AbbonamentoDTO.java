package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AbbonamentoDTO(@NotNull(message = "Il tipo di abbonamento è obbligatorio")
                             TipoAbbonamento tipoAbbonamento,

                             @Min(value = 0, message = "Il prezzo deve essere maggiore o uguale a 0")
                             double prezzo,

                             @NotNull(message = "La data di inizio è obbligatoria")
                             LocalDate dataInizio,

                             String descrizione,

                             Long utenteId) {
}
