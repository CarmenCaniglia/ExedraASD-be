package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Prodotto;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.ProdottoDTO;
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

    public Prodotto save(ProdottoDTO body){
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(body.nome());
        prodotto.setDescrizione(body.descrizione());
        prodotto.setPrezzo(body.prezzo());
        prodotto.setDisponibilità(body.disponibilità());
        prodotto.setImage(body.image());
        prodotto.setCategoria(body.categoria());
        return prodottoDAO.save(prodotto);
    }

    public Prodotto findById(long id){
        return prodottoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        Prodotto found = this.findById(id);
        prodottoDAO.delete(found);
    }

    public Prodotto findByIdAndUpdate(long id, ProdottoDTO body){
        Prodotto found = this.findById(id);
        found.setNome(body.nome());
        found.setDescrizione(body.descrizione());
        found.setPrezzo(body.prezzo());
        found.setDisponibilità(body.disponibilità());
        found.setImage(body.image());
        found.setCategoria(body.categoria());
        return prodottoDAO.save(found);
    }
}
