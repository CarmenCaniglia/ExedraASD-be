package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Corso;
import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.PrenotazioneDTO;
import carmencaniglia.exedraAsd.repositories.CorsoDAO;
import carmencaniglia.exedraAsd.repositories.PrenotazioneDAO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
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
    @Autowired
    private UtenteDAO utenteDAO;
    @Autowired
    private CorsoDAO corsoDAO;

    public Page<PrenotazioneCorso> getPrenotazioni(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return prenotazioneDAO.findAll(pageable);
    }

    public PrenotazioneCorso save(PrenotazioneDTO body){
        Utente utente = utenteDAO.findById(body.utenteId())
                .orElseThrow(()-> new NotFoundException("Utente con id: "+body.utenteId()+" non trovato!"));

        Corso corso = corsoDAO.findById(body.corsoId())
                .orElseThrow(()->new NotFoundException("Corso con id: " + body.corsoId() + " non trovato!"));


        PrenotazioneCorso prenotazione = new PrenotazioneCorso();
        prenotazione.setDataOra(body.dataOra());
        prenotazione.setUtente(utente);
        prenotazione.setCorso(corso);
        return prenotazioneDAO.save(prenotazione);
    }

    public PrenotazioneCorso findById(long id){
        return prenotazioneDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        PrenotazioneCorso found = this.findById(id);
        prenotazioneDAO.delete(found);
    }

    public PrenotazioneCorso findByIdAndUpdate(long id, PrenotazioneDTO body){
        PrenotazioneCorso found = this.findById(id);
        found.setDataOra(body.dataOra());
        return prenotazioneDAO.save(found);
    }
}
