package carmencaniglia.exedraAsd.payloads;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
