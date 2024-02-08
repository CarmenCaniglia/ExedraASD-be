package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.AbbonamentoDTO;
import carmencaniglia.exedraAsd.repositories.AbbonamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbbonamentoService {

    @Autowired
    private AbbonamentoDAO abbonamentoDAO;
    @Autowired
    private UtenteService utenteService;

    public Page<Abbonamento> getAbbonamenti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return abbonamentoDAO.findAll(pageable);
    }

    public Abbonamento save(AbbonamentoDTO body){
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setTipoAbbonamento(body.tipoAbbonamento());
        abbonamento.setPrezzo(body.prezzo());
        abbonamento.setDurata(body.durata());
        abbonamento.setDescrizione(body.descrizione());

        Utente utente = utenteService.findById(body.utenteId());
        abbonamento.setUtente(utente);
        return abbonamentoDAO.save(abbonamento);
    }

    public Abbonamento findById(long id){
        return abbonamentoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Abbonamento found = this.findById(id);
        abbonamentoDAO.delete(found);
    }

    public Abbonamento findByIdAndUpdate(long id, AbbonamentoDTO body){
        Abbonamento found = this.findById(id);
        found.setDescrizione(body.descrizione());
        found.setDurata(body.durata());
        found.setPrezzo(body.prezzo());
        found.setTipoAbbonamento(body.tipoAbbonamento());
        Utente utente = utenteService.findById(body.utenteId());
        found.setUtente(utente);
        return abbonamentoDAO.save(found);
    }

}
