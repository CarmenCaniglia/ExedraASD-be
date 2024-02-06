package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Prodotto;
import carmencaniglia.exedraAsd.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public List<Prodotto> getProdotti(){
        return prodottoService.getProdotti();
    }

    @GetMapping("/{id}")
    public Prodotto getProdottoById(@PathVariable long id){
        return prodottoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prodotto saveProdotto(@RequestBody Prodotto newProdottoPayload){
        return prodottoService.save(newProdottoPayload);
    }

    @PutMapping("/{id}")
    public Prodotto findByIdAndUpdate(@PathVariable long id,@RequestBody Prodotto modifiedProdottoPayload){
        return prodottoService.findByIdAndUpdate(id, modifiedProdottoPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        prodottoService.findByIdAndDelete(id);
    }
}
