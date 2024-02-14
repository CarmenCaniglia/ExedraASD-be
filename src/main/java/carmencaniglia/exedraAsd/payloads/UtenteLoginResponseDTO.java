package carmencaniglia.exedraAsd.payloads;

import carmencaniglia.exedraAsd.enums.Role;

public record UtenteLoginResponseDTO(String token, Role role) {
}
