package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Prodotto;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.ProdottoDTO;
import carmencaniglia.exedraAsd.payloads.ProdottoResponseDTO;
import carmencaniglia.exedraAsd.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public Page<Prodotto> getProdotti(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy){
        return prodottoService.getProdotti(page,size, orderBy);
    }

    @GetMapping("/{id}")
    public Prodotto getProdottoById(@PathVariable long id){
        return prodottoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdottoResponseDTO saveProdotto(@RequestBody @Validated ProdottoDTO newProdottoDTO, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            Prodotto savedProdotto = prodottoService.save(newProdottoDTO);
            return new ProdottoResponseDTO(savedProdotto.getId());
        }
    }

    @PutMapping("/{id}")
    public ProdottoResponseDTO findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated ProdottoDTO prodottoDTO){
        Prodotto updatedProdotto = prodottoService.findByIdAndUpdate(id,prodottoDTO);
        return new ProdottoResponseDTO(updatedProdotto.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        prodottoService.findByIdAndDelete(id);
    }

    @PostMapping("/{id}/upload")
    public String uploadImage (@PathVariable long id, @RequestParam("image")MultipartFile file) throws IOException {
        return prodottoService.uploadImage(id, file);
    }
}
