package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.SchedaNutrizionale;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.services.NutrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public SchedaNutrizionale saveSchedaNutrizionale(@RequestBody SchedaNutrizionale newNutrizionePayload){
        return nutrizioneService.save(newNutrizionePayload);
    }

    @PutMapping("/{id}")
    public SchedaNutrizionale findByIdAndUpdate(@PathVariable long id,@RequestBody SchedaNutrizionale modifiedNutrizionePayload){
        return nutrizioneService.findByIdAndUpdate(id, modifiedNutrizionePayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        nutrizioneService.findByIdAndDelete(id);
    }
}
