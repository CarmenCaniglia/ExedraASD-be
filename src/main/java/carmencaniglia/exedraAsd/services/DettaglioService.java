package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.DettaglioOrdine;
import carmencaniglia.exedraAsd.entities.Ordine;
import carmencaniglia.exedraAsd.entities.Prodotto;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.DettaglioDTO;
import carmencaniglia.exedraAsd.repositories.DettaglioDAO;
import carmencaniglia.exedraAsd.repositories.OrdineDAO;
import carmencaniglia.exedraAsd.repositories.ProdottoDAO;
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
    @Autowired
    private OrdineDAO ordineDAO;
    @Autowired
    private ProdottoDAO prodottoDAO;

    public Page<DettaglioOrdine> getDettagli(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return dettaglioDAO.findAll(pageable);
    }

    public DettaglioOrdine save(DettaglioDTO body){
        Ordine ordine = ordineDAO.findById(body.ordineId())
                .orElseThrow(() -> new NotFoundException("Ordine non trovato con ID: " + body.ordineId()));
        Prodotto prodotto = prodottoDAO.findById(body.prodottoId())
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato con ID: " + body.prodottoId()));
        DettaglioOrdine dettaglio = new DettaglioOrdine();
        dettaglio.setOrdine(ordine);
        dettaglio.setProdotto(prodotto);
        dettaglio.setQuantità(body.quantità());
        dettaglio.setPrezzo(body.prezzo());
        return dettaglioDAO.save(dettaglio);
    }

    public DettaglioOrdine findById(long id){
        return dettaglioDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        DettaglioOrdine found = this.findById(id);
        dettaglioDAO.delete(found);
    }

    public DettaglioOrdine findByIdAndUpdate(long id, DettaglioDTO body){
        DettaglioOrdine found = this.findById(id);

        Ordine ordine = ordineDAO.findById(body.ordineId())
                .orElseThrow(() -> new NotFoundException("Ordine non trovato con ID: " + body.ordineId()));
        Prodotto prodotto = prodottoDAO.findById(body.prodottoId())
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato con ID: " + body.prodottoId()));
        found.setOrdine(ordine);
        found.setProdotto(prodotto);
        found.setQuantità(body.quantità());
        found.setPrezzo(body.prezzo());
        return dettaglioDAO.save(found);
    }
}
