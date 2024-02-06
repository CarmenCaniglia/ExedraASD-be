package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.services.AbbonamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abbonamenti")
public class AbbonamentoController {

    @Autowired
    private AbbonamentoService abbonamentoService;

    @GetMapping
    public List<Abbonamento> getAbbonamenti(){
        return abbonamentoService.getAbbonamenti();
    }

    @GetMapping("/{id}")
    public Abbonamento getAbbonamentiById(@PathVariable long id){
        return abbonamentoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Abbonamento saveAbbonamento(@RequestBody Abbonamento newAbbonamentoPayload){
        return abbonamentoService.save(newAbbonamentoPayload);
    }

    @PutMapping("/{id}")
    public Abbonamento findByIdAndUpdate(@PathVariable long id,@RequestBody Abbonamento modifiedAbbonamentoPayload){
        return abbonamentoService.findByIdAndUpdate(id, modifiedAbbonamentoPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        abbonamentoService.findByIdAndDelete(id);
    }
}
