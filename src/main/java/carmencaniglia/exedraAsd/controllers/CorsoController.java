package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Corso;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.CorsoDTO;
import carmencaniglia.exedraAsd.payloads.CorsoResponseDTO;
import carmencaniglia.exedraAsd.services.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @GetMapping
    public Page<Corso> getCorsi(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return corsoService.getCorsi(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Corso getCorsiById(@PathVariable long id){
        return corsoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CorsoResponseDTO saveCorso(@RequestBody @Validated CorsoDTO corsoDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else{
            Corso savedCorso = corsoService.save(corsoDTO);
            return new CorsoResponseDTO(savedCorso.getId());
        }

    }

    @PutMapping("/{id}")
    public CorsoResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated CorsoDTO corsoDTO){
        Corso updatedCorso = corsoService.findByIdAndUpdate(id, corsoDTO);
        return new CorsoResponseDTO(updatedCorso.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        corsoService.findByIdAndDelete(id);
    }
}
