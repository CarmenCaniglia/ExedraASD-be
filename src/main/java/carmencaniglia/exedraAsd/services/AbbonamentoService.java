package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
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

    public Page<Abbonamento> getAbbonamenti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return abbonamentoDAO.findAll(pageable);
    }

    public Abbonamento save(Abbonamento body){
        return abbonamentoDAO.save(body);
    }

    public Abbonamento findById(long id){
        return abbonamentoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Abbonamento found = this.findById(id);
        abbonamentoDAO.delete(found);
    }

    public Abbonamento findByIdAndUpdate(long id, Abbonamento body){
        Abbonamento found = this.findById(id);
        found.setUtente(body.getUtente());
        found.setDescrizione(body.getDescrizione());
        found.setDurata(body.getDurata());
        found.setPrezzo(body.getPrezzo());
        found.setTipoAbbonamento(body.getTipoAbbonamento());
        return abbonamentoDAO.save(found);
    }

}
