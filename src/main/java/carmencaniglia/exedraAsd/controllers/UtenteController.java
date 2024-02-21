package carmencaniglia.exedraAsd.controllers;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.payloads.UtenteDTO;
import carmencaniglia.exedraAsd.payloads.UtenteResponseDTO;
import carmencaniglia.exedraAsd.services.AuthService;
import carmencaniglia.exedraAsd.services.UtenteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AuthService authService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy){
        return utenteService.getUtenti(page, size, orderBy);
    }

    @GetMapping("/me")
    @Transactional
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    }
    @PutMapping("/me")
    @Transactional
    public UtenteResponseDTO getMeAndUpdate(@AuthenticationPrincipal Utente currentUser, @RequestBody @Validated UtenteDTO body,BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException("Errori nel payload!");
        }

        Utente updatedUser = utenteService.findByIdAndUpdate(currentUser.getId(), body);
        return new UtenteResponseDTO(updatedUser.getId());
    }

    @DeleteMapping("/me")
    @Transactional
    public void getMeAndDelete(@AuthenticationPrincipal Utente currentUser){
        utenteService.findByIdAndDelete(currentUser.getId());
    }

    @GetMapping("/{id}")
    public Utente getUserById(@PathVariable long id){
        return utenteService.findById(id);
   }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public UtenteResponseDTO createUser(@RequestBody @Validated UtenteDTO newUserPayload, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload!");
        }else {
            Utente nuovoUtente = authService.save(newUserPayload);
            return new UtenteResponseDTO(nuovoUtente.getId());
        }
    }

    @PutMapping("/{id}")

    @Transactional
    public Utente findByIdAndUpdate(@PathVariable long id,@RequestBody @Validated UtenteDTO body,BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nella richiesta!");
        }
        return utenteService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        utenteService.findByIdAndDelete(id);
    }

    //endpoint per Multipart/Form-Data delle immagini
    @PostMapping("/{id}/upload")
    @Transactional
    public String uploadAvatar(@PathVariable long id,@RequestParam("avatar") MultipartFile file ) throws IOException {

        return utenteService.uploadPicture(id, file);
    }
}
