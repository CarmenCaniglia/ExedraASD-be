package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdottoDTO(@NotBlank(message = "Il nome è un campo obbligatorio!")
                          String nome,
                          @NotBlank(message = "La descrizione è un campo obbligatorio!")
                          String descrizione,
                          @Positive(message = "Il prezzo deve essere un valore positivo!")
                          double prezzo,
                          @PositiveOrZero(message = "La disponibilità deve essere un valore positivo o zero!")
                          int disponibilità,
                          @NotBlank(message = "L'immagine è un campo obbligatorio!")
                          String image,
                          @NotNull(message = "La categoria è un campo obbligatorio!")
                          Categoria categoria) {
}
