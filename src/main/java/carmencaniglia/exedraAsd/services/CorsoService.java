package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Corso;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.CorsoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {
    @Autowired
    private CorsoDAO corsoDAO;

    public List<Corso> getCorsi(){
        return corsoDAO.findAll();
    }

    public Corso save(Corso body){
        return corsoDAO.save(body);
    }

    public Corso findById(long id){
        return corsoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Corso found = this.findById(id);
        corsoDAO.delete(found);
    }

    public Corso findByIdAndUpdate(long id, Corso body){
        Corso found = this.findById(id);
        found.setNome(body.getNome());
        found.setDescrizione(body.getDescrizione());
        found.setOrario(body.getOrario());
        found.setMaxPartecipanti(body.getMaxPartecipanti());
        return corsoDAO.save(found);
    }
}
