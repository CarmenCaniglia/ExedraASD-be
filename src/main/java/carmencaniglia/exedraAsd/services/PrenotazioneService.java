package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.PrenotazioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneDAO prenotazioneDAO;

    public List<PrenotazioneCorso> getPrenotazioni(){
        return prenotazioneDAO.findAll();
    }

    public PrenotazioneCorso save(PrenotazioneCorso body){
        return prenotazioneDAO.save(body);
    }

    public PrenotazioneCorso findById(long id){
        return prenotazioneDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        PrenotazioneCorso found = this.findById(id);
        prenotazioneDAO.delete(found);
    }

    public PrenotazioneCorso findByIdAndUpdate(long id, PrenotazioneCorso body){
        PrenotazioneCorso found = this.findById(id);
        found.setDataOra(body.getDataOra());
        return prenotazioneDAO.save(found);
    }
}
