package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.enums.Role;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.UnauthorizedException;
import carmencaniglia.exedraAsd.payloads.UtenteDTO;
import carmencaniglia.exedraAsd.payloads.UtenteLoginDTO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import carmencaniglia.exedraAsd.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteDAO utenteDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UtenteLoginDTO body){
        Utente utente = utenteService.findByEmail(body.email());
        if(bcrypt.matches(body.password(),utente.getPassword())) {
            return jwtTools.createToken(utente);
        }else{
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public Utente save(UtenteDTO body){
        utenteDAO.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso!");
        });
        Utente nuovoUtente = new Utente();
        nuovoUtente.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        nuovoUtente.setNome(body.nome());
        nuovoUtente.setCognome(body.cognome());
        nuovoUtente.setEmail(body.email());
        nuovoUtente.setPassword(bcrypt.encode(body.password()));
        nuovoUtente.setRole(Role.USER); //body.role
        return utenteDAO.save(nuovoUtente);
    }
}
