package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.UtenteDTO;
import carmencaniglia.exedraAsd.payloads.UtenteLoginDTO;
import carmencaniglia.exedraAsd.payloads.UtenteLoginResponseDTO;
import carmencaniglia.exedraAsd.payloads.UtenteResponseDTO;
import carmencaniglia.exedraAsd.services.AuthService;
import carmencaniglia.exedraAsd.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body){
        String accessToken = authService.authenticateUser(body);
        return new UtenteLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponseDTO createUser (@RequestBody @Validated UtenteDTO newUserPayload, BindingResult validation){
        System.out.println(validation);
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        }else{
            Utente utente = authService.save(newUserPayload);
            return new UtenteResponseDTO(utente.getId());
        }
    }
}
