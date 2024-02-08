package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Ordine;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.OrdineDTO;
import carmencaniglia.exedraAsd.payloads.OrdineResponseDTO;
import carmencaniglia.exedraAsd.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @GetMapping
    public Page<Ordine> getOrdini(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy){
        return ordineService.getOrdini(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Ordine getOrdineById(@PathVariable long id){
        return ordineService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdineResponseDTO saveOrdine(@RequestBody @Validated OrdineDTO newOrdineDTO, BindingResult validation){
        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            Ordine savedOrdine = ordineService.save(newOrdineDTO);
            return new OrdineResponseDTO(savedOrdine.getId());
        }
    }

    @PutMapping("/{id}")
    public OrdineResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated OrdineDTO ordineDTO){
        Ordine updateOrdine = ordineService.findByIdAndUpdate(id,ordineDTO);
        return new OrdineResponseDTO(updateOrdine.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        ordineService.findByIdAndDelete(id);
    }
}
