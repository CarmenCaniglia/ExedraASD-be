package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.enums.Role;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.UtenteDTO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;


    public Page<Utente> getUtenti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return utenteDAO.findAll(pageable);
    }


    public Utente findById(long id){
        return utenteDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Utente found = this.findById(id);
        utenteDAO.delete(found);
    }

    public Utente findByIdAndUpdate(long id, Utente body){
        Utente found = this.findById(id);
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        return utenteDAO.save(found);
    }

    public Utente findByEmail(String email) throws NotFoundException{
        return utenteDAO.findByEmail(email).orElseThrow(()->new NotFoundException("Utente con email " + email + " non trovata!"));
    }


}
