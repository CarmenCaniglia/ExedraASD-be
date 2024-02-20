package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.Abbonamento;
import carmencaniglia.exedraAsd.entities.Utente;
import carmencaniglia.exedraAsd.enums.TipoAbbonamento;
import carmencaniglia.exedraAsd.exceptions.BadRequestException;
import carmencaniglia.exedraAsd.exceptions.BusinessException;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.payloads.AbbonamentoDTO;
import carmencaniglia.exedraAsd.repositories.AbbonamentoDAO;
import carmencaniglia.exedraAsd.repositories.UtenteDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbbonamentoService {

    @Autowired
    private AbbonamentoDAO abbonamentoDAO;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteDAO utenteDAO;

    public Page<Abbonamento> getAbbonamenti(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return abbonamentoDAO.findAll(pageable);
    }

    @Transactional
    public Abbonamento acquistaAbbonamento(AbbonamentoDTO abbonamentoDTO) {
        Utente utente = utenteDAO.findById(abbonamentoDTO.utenteId())
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        Abbonamento nuovoAbbonamento = new Abbonamento();
        nuovoAbbonamento.setUtente(utente);
        nuovoAbbonamento.setTipoAbbonamento(abbonamentoDTO.tipoAbbonamento());
        nuovoAbbonamento.setPrezzo(detPrezzo(abbonamentoDTO.tipoAbbonamento()));
        nuovoAbbonamento.setDescrizione(abbonamentoDTO.descrizione());
        nuovoAbbonamento.setDataInizio(LocalDate.now()); // Oppure abbonamentoDTO.getDataInizio() se la data di inizio è fornita
        nuovoAbbonamento.setDataFine(calcolaDataFine(nuovoAbbonamento.getDataInizio(), nuovoAbbonamento.getTipoAbbonamento()));

        return abbonamentoDAO.save(nuovoAbbonamento);
    }

    private LocalDate calcolaDataFine(LocalDate dataInizio, TipoAbbonamento tipoAbbonamento) {
        return switch (tipoAbbonamento) {
            case MENSILE -> dataInizio.plusMonths(1);
            case TRIMESTRALE -> dataInizio.plusMonths(3);
            case ANNUALE -> dataInizio.plusYears(1);
        };
    }

    @Transactional
    public Abbonamento save(AbbonamentoDTO body){
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setTipoAbbonamento(body.tipoAbbonamento());
        abbonamento.setPrezzo(detPrezzo(body.tipoAbbonamento()));
        abbonamento.setDescrizione(body.descrizione());
        abbonamento.setDataInizio(LocalDate.now());
        abbonamento.setDataFine(calcolaDataFine(abbonamento.getDataInizio(),abbonamento.getTipoAbbonamento()));

        if (body.utenteId() != null) {
            Utente utente = utenteDAO.findById(body.utenteId())
                    .orElseThrow(() -> new NotFoundException("Utente con id: " + body.utenteId() + " non trovato!"));
            abbonamento.setUtente(utente);
        }

        return abbonamentoDAO.save(abbonamento);
    }

    public Abbonamento findById(long id){
        return abbonamentoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id){
        Abbonamento found = this.findById(id);
        abbonamentoDAO.delete(found);
    }

    public Abbonamento findByIdAndUpdate(long id, AbbonamentoDTO body){
        Abbonamento found = this.findById(id);
        found.setDescrizione(body.descrizione());
        found.setDataInizio(LocalDate.now());
        found.setDataFine(calcolaDataFine(found.getDataInizio(),found.getTipoAbbonamento()));
        found.setPrezzo(detPrezzo(body.tipoAbbonamento()));
        found.setTipoAbbonamento(body.tipoAbbonamento());
        Utente utente = utenteDAO.findById(body.utenteId())
                .orElseThrow(() -> new NotFoundException("Utente con id: " + body.utenteId() + " non trovato!"));
        found.setUtente(utente);
        return abbonamentoDAO.save(found);
    }

    private double detPrezzo(TipoAbbonamento tipoAbbonamento){
        return switch (tipoAbbonamento){
            case MENSILE -> 50.0;
            case TRIMESTRALE -> 120.0;
            case ANNUALE -> 360.0;
        };
    }



}
