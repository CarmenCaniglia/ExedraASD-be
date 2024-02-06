package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Ordine;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.OrdineDAO;
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

    public Page<Ordine> getOrdini(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return ordineDAO.findAll(pageable);
    }

    public Ordine save(Ordine body){
        return ordineDAO.save(body);
    }

    public Ordine findById(long id){
        return ordineDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Ordine found = this.findById(id);
        ordineDAO.delete(found);
    }

    public Ordine findByIdAndUpdate(long id, Ordine body){
        Ordine found = this.findById(id);
        found.setDataOrdine(body.getDataOrdine());
        found.setStatoOrdine(body.getStatoOrdine());
        return ordineDAO.save(found);
    }
}
