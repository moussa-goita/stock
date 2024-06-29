package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.BonEntre;
import odk.kalanso.stock.entities.Fournisseur;
import odk.kalanso.stock.entities.Manager;
import odk.kalanso.stock.repositories.BonEntreRepository;
import odk.kalanso.stock.repositories.DetailsEntreRepository;
import odk.kalanso.stock.repositories.FournisseurRepository;
import odk.kalanso.stock.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntreService {
    @Autowired
    private BonEntreRepository bonEntreRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private ManagerRepository managerRepository;

    //liste des Bon Entre
    public List<BonEntre> bonEntreList(){
        return bonEntreRepository.findAll();
    }
    //Create BonEntre
    public BonEntre createBonEntre(BonEntre bonEntre,Long fournisseurId, Long managerId) {
        Fournisseur f = fournisseurRepository.findById(fournisseurId).orElse(null);
        Manager ma = managerRepository.findById(managerId).orElse(null);

        BonEntre saveBonEntre = null;
        if (f != null && ma != null) {
            bonEntre.setDateCommande(new Date());
            bonEntre.setFournisseur(f);
            bonEntre.setManager(ma);
            saveBonEntre = bonEntreRepository.save(bonEntre);
        }
        return saveBonEntre;
    }


}
