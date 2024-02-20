package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.UtenteDTO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;
    @Autowired
    private Cloudinary cloudinaryUploader;


    public Page<Utente> getUtenti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return utenteDAO.findAll(pageable);
    }


    public Utente findById(long id) {
        return utenteDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato."));
    }


    public void findByIdAndDelete(long id){
        Utente found = utenteDAO.findById(id).orElseThrow(()-> new NotFoundException("Utente con id " + id + " non trovato"));
        utenteDAO.delete(found);
    }

    public Utente findByIdAndUpdate(long id, UtenteDTO utenteDTO){
        Utente found = utenteDAO.findById(id).orElseThrow(()-> new NotFoundException("Utente con id " + id + " non trovato"));
        found.setNome(utenteDTO.nome());
        found.setCognome(utenteDTO.cognome());
        found.setEmail(utenteDTO.email());
        found.setRole(utenteDTO.role());
        return utenteDAO.save(found);
    }

    public Utente findByEmail(String email) throws NotFoundException{
        return utenteDAO.findByEmail(email).orElseThrow(()->new NotFoundException("Utente con email " + email + " non trovata!"));
    }

    public String uploadPicture (long id,MultipartFile file) throws IOException {
        Utente utente =utenteDAO.findById(id).orElseThrow(()->new NotFoundException(id));
       String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
       utente.setAvatar(url);
       utenteDAO.save(utente);
       return url;
    }

}
