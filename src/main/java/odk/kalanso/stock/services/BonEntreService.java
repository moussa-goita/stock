package odk.kalanso.stock.services;

import jakarta.transaction.Transactional;
import odk.kalanso.stock.entities.*;
import odk.kalanso.stock.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BonEntreService {

    @Autowired
    private BonEntreRepository bonEntreRepository;

    @Autowired
    private DetailsEntreRepository detailEntreRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private DetailsEntreRepository detailsEntreRepository;



    // Méthode pour récupérer tous les BonEntre
    public List<BonEntre> getAllBonEntres() {
        return bonEntreRepository.findAll();
    }

    // Méthode pour récupérer un BonEntre par son ID
    public Optional<BonEntre> getBonEntreById(Long id) {
        return bonEntreRepository.findById(id);
    }
    @Transactional
    public BonEntre creeBonEntre(BonEntre bonEntre) {
        if (bonEntre.getDateCommande() == null) {
            bonEntre.setDateCommande(new Date()); // Utilisation de la date actuelle
        }

        // Vérification et mise à jour des entités associées
        if (bonEntre.getManager() != null && bonEntre.getManager().getId() != null) {
            bonEntre.setManager(managerRepository.findById(bonEntre.getManager().getId()).orElse(null));
        }
        if (bonEntre.getAdmin() != null && bonEntre.getAdmin().getId() != null) {
            bonEntre.setAdmin(adminRepository.findById(bonEntre.getAdmin().getId()).orElse(null));
        }
        if (bonEntre.getFournisseur() != null && bonEntre.getFournisseur().getId() != null) {
            bonEntre.setFournisseur(fournisseurRepository.findById(bonEntre.getFournisseur().getId()).orElse(null));
        }

        // Sauvegarder le BonEntre
        bonEntre = bonEntreRepository.save(bonEntre);

        // Mettre à jour la quantité du produit pour chaque DetailsEntre
        for (DetailsEntre detailsEntre : bonEntre.getDetailsEntres()) {
            detailsEntre.setBonEntre(bonEntre);
            if (detailsEntre.getProduit() != null && detailsEntre.getProduit().getId() != null) {
                Produit produit = produitRepository.findById(detailsEntre.getProduit().getId()).orElse(null);
                if (produit != null) {
                    // Ajouter la quantité spécifiée à la quantité actuelle du produit
                    produit.setQte(produit.getQte() + Integer.parseInt(String.valueOf(detailsEntre.getQte())));
                    produitRepository.save(produit);
                }
                detailsEntre.setProduit(produit);
            }
            detailsEntreRepository.save(detailsEntre);
        }

        return bonEntre;
    }
    // Méthode pour mettre à jour un BonEntre existant
    public BonEntre updateBonEntre(Long id, BonEntre bonEntre) {
        Optional<BonEntre> existingBonEntreOptional = bonEntreRepository.findById(id);
        if (existingBonEntreOptional.isEmpty()) {
            // Gérer l'exception ou retourner null selon votre logique
            return null;
        }

        BonEntre existingBonEntre = existingBonEntreOptional.get();
        existingBonEntre.setDateCommande(bonEntre.getDateCommande());
        existingBonEntre.setStatut(bonEntre.getStatut());
        existingBonEntre.setDetailsEntres(bonEntre.getDetailsEntres());
        existingBonEntre.setManager(bonEntre.getManager());
        existingBonEntre.setAdmin(bonEntre.getAdmin());
        existingBonEntre.setFournisseur(bonEntre.getFournisseur());

        // Sauvegarder et retourner le BonEntre mis à jour
        return bonEntreRepository.save(existingBonEntre);
    }

    // Méthode pour supprimer un BonEntre par son ID
    public void deleteBonEntre(Long id) {
        bonEntreRepository.deleteById(id);
    }
    // Méthode pour récupérer les DetailsEntre d'un BonEntre par son ID
    public List<DetailsEntre> getDetailsEntresByBonEntreId(Long bonEntreId) {
        Optional<BonEntre> bonEntreOptional = bonEntreRepository.findById(bonEntreId);
        return bonEntreOptional.map(BonEntre::getDetailsEntres).orElse(null);
    }
    // Méthode pour récupérer un DetailsEntre par son ID
    public Optional<DetailsEntre> getDetailsEntreById(Long id) {
        return detailsEntreRepository.findById(id);
    }
}