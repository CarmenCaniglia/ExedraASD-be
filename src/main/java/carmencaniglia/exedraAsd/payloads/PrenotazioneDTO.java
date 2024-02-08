package carmencaniglia.exedraAsd.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PrenotazioneDTO(@NotNull(message = "L'ID dell'utente è obbligatorio") long utenteId,
                              @NotNull(message = "L'ID del corso è obbligatorio") long corsoId,
                              @NotNull(message = "La data e l'ora della prenotazione sono obbligatorie") Date dataOra) {
}
