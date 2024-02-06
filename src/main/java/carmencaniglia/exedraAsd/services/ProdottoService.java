package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Prodotto;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.ProdottoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoDAO prodottoDAO;

    public Page<Prodotto> getProdotti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return prodottoDAO.findAll(pageable);
    }

    public Prodotto save(Prodotto body){
        return prodottoDAO.save(body);
    }

    public Prodotto findById(long id){
        return prodottoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Prodotto found = this.findById(id);
        prodottoDAO.delete(found);
    }

    public Prodotto findByIdAndUpdate(long id, Prodotto body){
        Prodotto found = this.findById(id);
        found.setNome(body.getNome());
        found.setDescrizione(body.getDescrizione());
        found.setPrezzo(body.getPrezzo());
        found.setDisponibilità(body.getDisponibilità());
        found.setImage(body.getImage());
        found.setCategoria(body.getCategoria());
        return prodottoDAO.save(found);
    }
}
