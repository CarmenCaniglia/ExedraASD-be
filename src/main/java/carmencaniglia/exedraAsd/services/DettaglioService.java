package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.DettaglioOrdine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.DettaglioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DettaglioService {
    @Autowired
    private DettaglioDAO dettaglioDAO;

    public Page<DettaglioOrdine> getDettagli(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return dettaglioDAO.findAll(pageable);
    }

    public DettaglioOrdine save(DettaglioOrdine body){
        return dettaglioDAO.save(body);
    }

    public DettaglioOrdine findById(long id){
        return dettaglioDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        DettaglioOrdine found = this.findById(id);
        dettaglioDAO.delete(found);
    }

    public DettaglioOrdine findByIdAndUpdate(long id, DettaglioOrdine body){
        DettaglioOrdine found = this.findById(id);
        found.setProdotto(body.getProdotto());
        found.setQuantità(body.getQuantità());
        found.setPrezzo(body.getPrezzo());
        return dettaglioDAO.save(found);
    }
}
