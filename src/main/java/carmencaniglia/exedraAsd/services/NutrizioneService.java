package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.SchedaNutrizionale;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.NutrizioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutrizioneService {
    @Autowired
    private NutrizioneDAO nutrizioneDAO;

    public List<SchedaNutrizionale> getSchedeNutrizionali(){
        return nutrizioneDAO.findAll();
    }

    public SchedaNutrizionale save(SchedaNutrizionale body){


        return nutrizioneDAO.save(body);
    }

    public SchedaNutrizionale findById(long id){
        return nutrizioneDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        SchedaNutrizionale found = this.findById(id);
        nutrizioneDAO.delete(found);
    }

    public SchedaNutrizionale findByIdAndUpdate(long id, SchedaNutrizionale body){
        SchedaNutrizionale found = this.findById(id);
        found.setTitolo(body.getTitolo());
        found.setDescrizione(body.getDescrizione());
        return nutrizioneDAO.save(found);
    }
}
