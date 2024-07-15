package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Motif;
import odk.kalanso.stock.exception.MotifNotFoundException;
import odk.kalanso.stock.repositories.MotifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotifService {
    @Autowired
    private MotifRepository motifRepository;

    //List des Motifs
    public List<Motif> getAllMotifs() {
        return motifRepository.findAll();
    }
    //GET par Id
    public Optional<Motif> getMotifById(int id) {
        Optional<Motif> existMotif = motifRepository.findById(id);
        if (existMotif.isPresent()) {
            throw new MotifNotFoundException(String.format("motif id %s nest pas trouve" + id));
        }
        return motifRepository.findById(id);
    }
    //Create new Motif
    public Motif createMotif(Motif motif) {
        return motifRepository.save(motif);
    }
    //Modifier Motif
    public Motif updateMotif(Motif motif, int id) {
        Optional<Motif> existMotif = motifRepository.findById(id);
        if (existMotif.isPresent()) {
            throw new MotifNotFoundException(String.format("motif id %s nest pas trouve" + id));
        }
        return motifRepository.save(motif);
    }
    //Delete Motif
    public void deleteMotif(int id) {
        Optional<Motif> existMotif = motifRepository.findById(id);
        if (existMotif.isPresent()) {
            throw new MotifNotFoundException(String.format("motif id %s nest pas trouve" + id));
        }
         motifRepository.deleteById(id);
    }
}
