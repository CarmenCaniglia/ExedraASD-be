package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.SchedaNutrizionale;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.NutrizioneDTO;
import carmencaniglia.exedraAsd.payloads.NutrizioneResponseDTO;
import carmencaniglia.exedraAsd.services.NutrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedeNutrizione")
public class NutrizioneController {
    @Autowired
    private NutrizioneService nutrizioneService;

    @GetMapping
    public Page<SchedaNutrizionale> getSchedeNutrizionali(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "id") String orderBy){
        return nutrizioneService.getSchedeNutrizionali(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public SchedaNutrizionale getSchedaNutrizionaleById(@PathVariable long id){
        return nutrizioneService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NutrizioneResponseDTO saveSchedaNutrizionale(@RequestBody @Validated NutrizioneDTO newNutrizioneDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            SchedaNutrizionale savedScheda = nutrizioneService.save(newNutrizioneDTO);
            return new NutrizioneResponseDTO(savedScheda.getId());
        }
    }

    @PutMapping("/{id}")
    public NutrizioneResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated NutrizioneDTO nutrizioneDTO){
        SchedaNutrizionale updatedScheda = nutrizioneService.findByIdAndUpdate(id,nutrizioneDTO);
        return new NutrizioneResponseDTO(updatedScheda.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        nutrizioneService.findByIdAndDelete(id);
    }
}
