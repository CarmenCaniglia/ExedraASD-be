package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UtenteLoginResponseDTO(@JsonProperty("token")String token,@JsonProperty("role") Role role) {
}
