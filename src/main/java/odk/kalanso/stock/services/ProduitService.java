package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Produit;
import odk.kalanso.stock.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    //Liste produits
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }
    // Get  Produit BY Id
    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }
    //Create Produit
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    //Update Produit
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    //Delete produit
    public void deleteProduit(Long id) {
         produitRepository.deleteById(id);
    }
}
