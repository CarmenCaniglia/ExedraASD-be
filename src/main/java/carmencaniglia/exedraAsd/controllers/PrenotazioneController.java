package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.PrenotazioneCorso;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.PrenotazioneDTO;
import carmencaniglia.exedraAsd.payloads.PrenotazioneResponseDTO;
import carmencaniglia.exedraAsd.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<PrenotazioneCorso> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "id") String orderBy){
        return prenotazioneService.getPrenotazioni(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public PrenotazioneCorso getPrenotazioneById(@PathVariable long id){
        return prenotazioneService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponseDTO savePrenotazione(@RequestBody @Validated PrenotazioneDTO newPrenotazioneDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            PrenotazioneCorso savedPrenotazione = prenotazioneService.save(newPrenotazioneDTO);
            return new PrenotazioneResponseDTO(savedPrenotazione.getId());
        }
    }

    @PutMapping("/{id}")
    public PrenotazioneResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated PrenotazioneDTO prenotazioneDTO){
        PrenotazioneCorso updatedPrenotazione = prenotazioneService.findByIdAndUpdate(id, prenotazioneDTO);
        return new PrenotazioneResponseDTO(updatedPrenotazione.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        prenotazioneService.findByIdAndDelete(id);
    }
}
