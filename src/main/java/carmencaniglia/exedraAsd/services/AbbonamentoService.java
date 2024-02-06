package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.AbbonamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbbonamentoService {

    @Autowired
    private AbbonamentoDAO abbonamentoDAO;

    public List<Abbonamento> getAbbonamenti(){
        return abbonamentoDAO.findAll();
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
