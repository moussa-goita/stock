package odk.kalanso.stock.services;

import jakarta.transaction.Transactional;
import odk.kalanso.stock.entities.*;
import odk.kalanso.stock.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BonSortieService {

    @Autowired
    private BonSortieRepository bonSortieRepository;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private DetailsSortieRepository detailsSortieRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private MotifRepository motifRepository;

    // Méthode pour récupérer tous les BonSortie
    public List<BonSortie> getAllBonSorties() {
        return bonSortieRepository.findAll();
    }

    // Méthode pour récupérer un BonSortie par son ID
    public Optional<BonSortie> getBonSortieById(Long id) {
        return bonSortieRepository.findById(id);
    }

    // Méthode pour créer un nouveau BonSortie
   // @Transactional
//    public BonSortie createBonSortie(BonSortie bonSortie) {
//        try {
//            if (bonSortie.getDateSortie() == null) {
//                bonSortie.setDateSortie(new Date());
//            }
//            // Vérification et mise à jour des entités associées
//            if (bonSortie.getManager() != null && bonSortie.getManager().getId() != null) {
//                bonSortie.setManager(managerRepository.findById(bonSortie.getManager().getId()).orElse(null));
//            }
//            if (bonSortie.getAdmin() != null && bonSortie.getAdmin().getId() != null) {
//                bonSortie.setAdmin(adminRepository.findById(bonSortie.getAdmin().getId()).orElse(null));
//            }
//            for (DetailsSortie detailsSortie : bonSortie.getDetailsSorties()) {
//                Produit produit = detailsSortie.getProduit();
//                if (produit == null || produit.getId() == null) {
//                    throw new IllegalArgumentException("Produit invalide pour le détail de sortie");
//                }
//
//                // Récupérer le produit depuis la base de données
//                Produit finalProduit = produitRepository.findById(produit.getId())
//                        .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé pour l'ID: " + produit.getId()));
//
//                // Calculer la nouvelle quantité
//                int quantiteSortie = Integer.parseInt(detailsSortie.getQte());
//                int nouvelleQuantite = finalProduit.getQte() - quantiteSortie;
//
//                if (nouvelleQuantite < 0) {
//                    throw new IllegalArgumentException("Quantité insuffisante pour le produit: " + finalProduit.getNom());
//                }
//
//                // Mettre à jour la quantité du produit
//                finalProduit.setQte(nouvelleQuantite);
//                produitRepository.save(finalProduit);
//            }
//
//            bonSortie = bonSortieRepository.save(bonSortie);
//
//            for (DetailsSortie detailsSortie : bonSortie.getDetailsSorties()) {
//                detailsSortie.setBonSortie(bonSortie);
//                detailsSortieRepository.save(detailsSortie);
//            }
//
//            return bonSortie;
//        } catch (Exception e) {
//            throw new RuntimeException("Erreur lors de la création du bon de sortie", e);
//        }
//    }
    @Transactional
    public BonSortie createBonSortie(BonSortie bonSortie) {
        try {
            // Sauvegarder le BonSortie
            bonSortie = bonSortieRepository.save(bonSortie);

            // Vérification et mise à jour des entités associées

            if (bonSortie.getManager() != null && bonSortie.getManager().getId() != null) {
                bonSortie.setManager(managerRepository.findById(bonSortie.getManager().getId()).orElse(null));
            }
            if (bonSortie.getAdmin() != null && bonSortie.getAdmin().getId() != null) {
                bonSortie.setAdmin(adminRepository.findById(bonSortie.getAdmin().getId()).orElse(null));
            }
            // Mettre à jour la quantité du produit pour chaque DetailsSortie
            for (DetailsSortie detailsSortie : bonSortie.getDetailsSorties()) {
                detailsSortie.setBonSortie(bonSortie);
                if (detailsSortie.getProduit() != null && detailsSortie.getProduit().getId() != null) {
                    Produit produit = produitRepository.findById(detailsSortie.getProduit().getId()).orElse(null);
                    if (produit != null) {
                        int nouveauQte = produit.getQte() - Integer.parseInt(detailsSortie.getQte());
                        if (nouveauQte < 0) {
                            throw new IllegalArgumentException("Quantité insuffisante pour le produit: " + produit.getNom());
                        }
                        produit.setQte(nouveauQte);
                        produitRepository.save(produit);

                        // Vérifier si la quantité atteint 5 et créer une notification
                        if (nouveauQte <= 5) {
                            String message = "La quantité du produit " + produit.getNom() + " est maintenant " + nouveauQte + "Pensez à faire une nouvelle commande pour ce produit.";
                            notificationService.createNotification(message, bonSortie.getManager());
                        }
                    }
                    detailsSortie.setProduit(produit);
                }
                detailsSortieRepository.save(detailsSortie);
            }

            return bonSortie;
        } catch (IllegalArgumentException e) {
            // Log l'exception et renvoie une réponse appropriée
            System.err.println("Erreur: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // Log l'exception et renvoie une réponse appropriée
            System.err.println("Erreur inconnue: " + e.getMessage());
            throw new RuntimeException("Erreur lors de la création du bon de sortie", e);
        }
    }

    // Méthode pour mettre à jour un BonSortie existant
    @Transactional
    public BonSortie updateBonSortie(Long id, BonSortie bonSortie) {
        Optional<BonSortie> existingBonSortieOptional = bonSortieRepository.findById(id);
        if (existingBonSortieOptional.isEmpty()) {
            // Gérer l'exception ou retourner null selon votre logique
            return null;
        }

        BonSortie existingBonSortie = existingBonSortieOptional.get();
        existingBonSortie.setMotif(bonSortie.getMotif());
        existingBonSortie.setDateSortie(bonSortie.getDateSortie());
        existingBonSortie.setDetailsSorties(bonSortie.getDetailsSorties());
        existingBonSortie.setManager(bonSortie.getManager());
        existingBonSortie.setAdmin(bonSortie.getAdmin());

        // Sauvegarder et retourner le BonSortie mis à jour
        return bonSortieRepository.save(existingBonSortie);
    }

    // Méthode pour supprimer un BonSortie par son ID
    public void deleteBonSortie(Long id) {
        bonSortieRepository.deleteById(id);
    }

    // Méthode pour récupérer les DetailsSortie d'un BonSortie par son ID
    public List<DetailsSortie> getDetailsSortiesByBonSortieId(Long bonSortieId) {
        Optional<BonSortie> bonSortieOptional = bonSortieRepository.findById(bonSortieId);
        return bonSortieOptional.map(BonSortie::getDetailsSorties).orElse(null);
    }

    // Méthode pour récupérer un DetailsSortie par son ID
    public Optional<DetailsSortie> getDetailsSortieById(Long id) {
        return detailsSortieRepository.findById(id);
    }
    //Produit la plus sortie en fonction du motif
    public Map<String, Map<String, Integer>> getTopProductsByMotif() {
        List<BonSortie> bonSorties = bonSortieRepository.findAll();
        Map<String, Map<String, Integer>> topProductsByMotif = new HashMap<>();

        for (BonSortie bonSortie : bonSorties) {
            Motif motif = bonSortie.getMotif();
            if (motif == null) {
                continue; // Ignorer les bons de sortie sans motif
            }
            String motifTitle = motif.getTitle();
            Map<String, Integer> productCountMap = topProductsByMotif.getOrDefault(motifTitle, new HashMap<>());

            for (DetailsSortie detailsSortie : bonSortie.getDetailsSorties()) {
                Produit produit = detailsSortie.getProduit();
                if (produit != null) {
                    String productName = produit.getNom();
                    productCountMap.put(productName, Integer.valueOf(productCountMap.getOrDefault(productName, 0) + detailsSortie.getQte()));
                }
            }

            topProductsByMotif.put(motifTitle, productCountMap);
        }

        return topProductsByMotif;
    }
}
