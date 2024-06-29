package odk.kalanso.stock.services;

import jakarta.transaction.Transactional;
import odk.kalanso.stock.entities.*;
import odk.kalanso.stock.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BonEntreService {
    @Autowired
    private BonEntreRepository bonEntreRepository;

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private EntreService entreService;

    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private DetailsEntreRepository detailsEntreRepository;
    @Autowired
    private ManagerRepository managerRepository;

    //Liste Entre
    public List<DetailsEntre> allEntre(){
        return detailsEntreRepository.findAll();
    }
    //Ajouter Entre
//    public DetailsEntre createEntre(DetailsEntre detailsEntre, BonEntre bonEntre, long produitId, long fournisseurId, long managerId ){
//        Produit p = produitRepository.findById(produitId).orElse(null);
//        Fournisseur f = fournisseurRepository.findById(fournisseurId).orElse(null);
//        Manager ma = managerRepository.findById(managerId).orElse(null);
//        bonEntre.setDateCommande(new Date());
//        BonEntre bon = entreService.createBonEntre(bonEntre, fournisseurId, managerId);
//        detailsEntre.setBonEntre(bon);
//        detailsEntre.setProduit(p);
//        detailsEntreRepository.save(detailsEntre);
//
//        return detailsEntreRepository.save(detailsEntre);
//    }
    //Delete Entre
    public void deleteEntre(Long id){
        detailsEntreRepository.deleteById(id);
    }
    //Get by id BonEntre
    public Optional<DetailsEntre> getBonEntreById(Long id){
        return detailsEntreRepository.findById(id);
    }
    @Transactional
    public BonEntre creerBonEntreAvecDetails(BonEntre bonEntre, List<DetailsEntre> detailsEntres) {
        // Enregistrer BonEntre
        BonEntre bonEntreEnregistre = bonEntreRepository.save(bonEntre);
System.out.println("bon sauve1");
        // Enregistrer les entités DetailsEntre avec référence au BonEntre enregistré
        for (DetailsEntre detailsEntre : detailsEntres) {
            detailsEntre.setBonEntre(bonEntreEnregistre);
            System.out.println("bon sauve2");

            detailsEntreRepository.save(detailsEntre);
            System.out.println("Details sauve");

        }

        return bonEntreEnregistre;
    }
}
