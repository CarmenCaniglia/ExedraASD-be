package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Ordine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.OrdineDTO;
import carmencaniglia.exedraAsd.repositories.OrdineDAO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineDAO ordineDAO;
    @Autowired
    private UtenteDAO utenteDAO;

    public Page<Ordine> getOrdini(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return ordineDAO.findAll(pageable);
    }

    public Ordine save(OrdineDTO body){
        Utente utente = utenteDAO.findById(body.utenteId())
                .orElseThrow(()-> new NotFoundException("Utente con id: "+body.utenteId()+" non trovato!"));

        Ordine ordine = new Ordine();
        ordine.setUtente(utente);
        ordine.setDataOrdine(body.dataOrdine());
        ordine.setStatoOrdine(body.statoOrdine());
        return ordineDAO.save(ordine);
    }

    public Ordine findById(long id){
        return ordineDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Ordine found = this.findById(id);
        ordineDAO.delete(found);
    }

    public Ordine findByIdAndUpdate(long id, OrdineDTO body){
        Ordine found = this.findById(id);
        found.setDataOrdine(body.dataOrdine());
        found.setStatoOrdine(body.statoOrdine());
        return ordineDAO.save(found);
    }
}
