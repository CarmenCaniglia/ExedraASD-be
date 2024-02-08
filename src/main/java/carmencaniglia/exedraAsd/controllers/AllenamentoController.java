package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.SchedaAllenamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.AllenamentoDTO;
import carmencaniglia.exedraAsd.payloads.AllenamentoResponseDTO;
import carmencaniglia.exedraAsd.services.AllenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedeAllenamenti")
public class AllenamentoController {
    @Autowired
    private AllenamentoService allenamentoService;

    @GetMapping
    public Page<SchedaAllenamento> getSchedeAllenamento(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String orderBy){
        return allenamentoService.getSchedeAllenamento(page,size,orderBy);
    }

    @GetMapping("/{id}")
    public SchedaAllenamento getSchedaAllenamentoById(@PathVariable long id){
        return allenamentoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AllenamentoResponseDTO saveSchedaAllenamento(@RequestBody @Validated AllenamentoDTO newAllenamentoDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            SchedaAllenamento savedAllenamento = allenamentoService.save(newAllenamentoDTO);
            return new AllenamentoResponseDTO(savedAllenamento.getId());
        }
    }

    @PutMapping("/{id}")
    public AllenamentoResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated AllenamentoDTO allenamentoDTO){
        SchedaAllenamento updatedAllenamento = allenamentoService.findByIdAndUpdate(id, allenamentoDTO);
        return new AllenamentoResponseDTO(updatedAllenamento.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        allenamentoService.findByIdAndDelete(id);
    }
}
