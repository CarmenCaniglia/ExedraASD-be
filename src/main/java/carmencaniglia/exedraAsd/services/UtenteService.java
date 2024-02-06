package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Utente save(Utente body){
        utenteDAO.findByEmail(body.getEmail()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso!");
        });
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        return utenteDAO.save(body);
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


}
