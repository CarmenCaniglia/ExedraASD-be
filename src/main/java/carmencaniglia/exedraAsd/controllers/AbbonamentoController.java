package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.AbbonamentoDTO;
import carmencaniglia.exedraAsd.payloads.AbbonamentoResponseDTO;
import carmencaniglia.exedraAsd.services.AbbonamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abbonamenti")
public class AbbonamentoController {

    @Autowired
    private AbbonamentoService abbonamentoService;

    @GetMapping
    public Page<Abbonamento> getAbbonamenti(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String orderBy){
        return abbonamentoService.getAbbonamenti(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Abbonamento getAbbonamentiById(@PathVariable long id){
        return abbonamentoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public AbbonamentoResponseDTO saveAbbonamento(@RequestBody @Validated AbbonamentoDTO newAbbonamentoDTO, BindingResult validation){
        System.out.println("Utente ID: " + newAbbonamentoDTO.utenteId());
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else{
            Abbonamento savedAbbonamento = abbonamentoService.save(newAbbonamentoDTO);
            return new AbbonamentoResponseDTO(savedAbbonamento.getId());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AbbonamentoResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated AbbonamentoDTO abbonamentoDTO){
        Abbonamento updatedAbbonamento = abbonamentoService.findByIdAndUpdate(id, abbonamentoDTO);
        return new AbbonamentoResponseDTO(updatedAbbonamento.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable long id){
        abbonamentoService.findByIdAndDelete(id);
    }
}
