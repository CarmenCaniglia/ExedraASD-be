package carmencaniglia.exedraAsd.services;

import carmencaniglia.exedraAsd.entities.SchedaAllenamento;
import carmencaniglia.exedraAsd.exceptions.NotFoundException;
import carmencaniglia.exedraAsd.repositories.AllenamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllenamentoService {
    @Autowired
    private AllenamentoDAO allenamentoDAO;

    public Page<SchedaAllenamento> getSchedeAllenamento(int page, int size, String orderBy){
        if(size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return allenamentoDAO.findAll(pageable);
    }

    public SchedaAllenamento save(SchedaAllenamento body){

        return allenamentoDAO.save(body);
    }

    public SchedaAllenamento findById(long id){
        return allenamentoDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    public void findByIdAndDelete(long id){
        SchedaAllenamento found = this.findById(id);
        allenamentoDAO.delete(found);
    }

    public SchedaAllenamento findByIdAndUpdate(long id, SchedaAllenamento body){
        SchedaAllenamento found = this.findById(id);
        found.setTitolo(body.getTitolo());
        found.setDescrizione(body.getDescrizione());
        return allenamentoDAO.save(found);
    }
}
