package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.PrenotazioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneDAO prenotazioneDAO;

    public Page<PrenotazioneCorso> getPrenotazioni(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return prenotazioneDAO.findAll(pageable);
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
