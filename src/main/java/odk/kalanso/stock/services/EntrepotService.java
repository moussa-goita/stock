package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Entrepot;
import odk.kalanso.stock.entities.Motif;
import odk.kalanso.stock.exception.EntrepotNotFoundException;
import odk.kalanso.stock.repositories.EntrepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepotService {
    @Autowired
    private EntrepotRepository entrepotRepository;
    //  List des entrepots
    public List<Entrepot> getAllEntrepot() {
        return entrepotRepository.findAll();
    }
    //GET par Id
    public Optional<Entrepot> getEntrepotById(Long id) {
        return entrepotRepository.findById(id);
    }
    //Create new Entrepot
    public Entrepot createEntrepot(Entrepot entrepot) {
        return entrepotRepository.save(entrepot);
    }
    //Modifier Entrepot
    public Entrepot updateEntrepot(Entrepot entrepot, long id) {
        Optional<Entrepot> existEntrepot = entrepotRepository.findById(id);
        if (existEntrepot.isPresent()) {
            throw new EntrepotNotFoundException(String.format("entrepot id %s nest pas trouve"));
        }
        return entrepotRepository.save(entrepot);
    }
    //Delete Entrepot
    public void deleteEntrepot(long id) {
        Optional<Entrepot> existEntrepot = entrepotRepository.findById(id);
        if (existEntrepot.isPresent()) {
            throw new EntrepotNotFoundException(String.format("entrepot id %s nest pas trouve"));
        }
         entrepotRepository.deleteById(id);
    }
}
