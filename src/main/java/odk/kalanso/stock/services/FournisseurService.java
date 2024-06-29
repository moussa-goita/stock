package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Fournisseur;
import odk.kalanso.stock.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FournisseurService {

    @Autowired
    private  FournisseurRepository fournisseurRepository;

    //Liste All fournisseur
    public  List<Fournisseur> allFournisseurs() {
        return  fournisseurRepository.findAll();
    }
    //Create Fournisseur
    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
    //GetById
    public Optional<Fournisseur> getFournisseurById(Long id) {
        return fournisseurRepository.findById(id);
    }
    //Modifier Fournisseur
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
}
