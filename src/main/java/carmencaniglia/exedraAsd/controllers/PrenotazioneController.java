package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import carmencaniglia.exedraAsd.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public List<PrenotazioneCorso> getPrenotazioni(){
        return prenotazioneService.getPrenotazioni();
    }

    @GetMapping("/{id}")
    public PrenotazioneCorso getPrenotazioneById(@PathVariable long id){
        return prenotazioneService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneCorso savePrenotazione(@RequestBody PrenotazioneCorso newPrenotazionePayload){
        return prenotazioneService.save(newPrenotazionePayload);
    }

    @PutMapping("/{id}")
    public PrenotazioneCorso findByIdAndUpdate(@PathVariable long id,@RequestBody PrenotazioneCorso modifiedPrenotazionePayload){
        return prenotazioneService.findByIdAndUpdate(id, modifiedPrenotazionePayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        prenotazioneService.findByIdAndDelete(id);
    }
}
