package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.SchedaAllenamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.services.AllenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public SchedaAllenamento saveSchedaAllenamento(@RequestBody SchedaAllenamento newAllenamentoPayload){
        return allenamentoService.save(newAllenamentoPayload);
    }

    @PutMapping("/{id}")
    public SchedaAllenamento findByIdAndUpdate(@PathVariable long id,@RequestBody SchedaAllenamento modifiedAllenamentoPayload){
        return allenamentoService.findByIdAndUpdate(id, modifiedAllenamentoPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        allenamentoService.findByIdAndDelete(id);
    }
}
