package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Utente> getUsers(){
        return utenteService.getUtenti();
    }

    @GetMapping("/{id}")
    public Utente getUserById(@PathVariable long id){
        return utenteService.findById(id);
   }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody Utente newUserPayload){
        return utenteService.save(newUserPayload);
    }

    @PutMapping("/{id}")
    public Utente findByIdAndUpdate(@PathVariable long id,@RequestBody Utente modifiedUserPayload){
        return utenteService.findByIdAndUpdate(id, modifiedUserPayload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        utenteService.findByIdAndDelete(id);
    }
}
