package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.DettaglioOrdine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.services.DettaglioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dettaglioOrdine")
public class DettaglioController {

    @Autowired
    private DettaglioService dettaglioService;

    @GetMapping
    public Page<DettaglioOrdine> getDettagli(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String orderBy){
        return dettaglioService.getDettagli(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public DettaglioOrdine getDettaglioById(@PathVariable long id){
        return dettaglioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DettaglioOrdine saveDettaglio(@RequestBody DettaglioOrdine newDettaglioPayload){
        return dettaglioService.save(newDettaglioPayload);
    }

    @PutMapping("/{id}")
    public DettaglioOrdine findByIdAndUpdate(@PathVariable long id,@RequestBody DettaglioOrdine modifiedDettaglioPayload){
        return dettaglioService.findByIdAndUpdate(id, modifiedDettaglioPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        dettaglioService.findByIdAndDelete(id);
    }
}
