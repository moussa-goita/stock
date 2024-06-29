package odk.kalanso.stock.controllers;

import odk.kalanso.stock.entities.BonEntre;
import odk.kalanso.stock.entities.DetailsEntre;
import odk.kalanso.stock.entities.Fournisseur;
import odk.kalanso.stock.entities.Produit;
import odk.kalanso.stock.services.BonEntreService;
import odk.kalanso.stock.services.EntreService;
import odk.kalanso.stock.services.FournisseurService;
import odk.kalanso.stock.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private BonEntreService bonEntreService;

    @Autowired
    private EntreService entreService;
    //Fournisseur
    //create Fournisseur
    @PostMapping ("/fournisseur/create")
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.createFournisseur(fournisseur);
    }
    @GetMapping("/founisseur/all")
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.allFournisseurs();
    }

    //get Id
    @GetMapping("/formateur/{id}")
    public Optional<Fournisseur> getFournisseur(@PathVariable Long id) {
        return fournisseurService.getFournisseurById(id);
    }
    //Update Fournisseur

    //Produit
    //Create Produit
    @PostMapping("/produit/create")
    public Produit createProduit(@RequestBody Produit produit){
        return produitService.createProduit(produit);
    }
    //Liste produit
    @GetMapping("/produit/all")
    public List<Produit> listProduit(){
        return produitService.listProduit();
    }

    //Delete Produit
    @PutMapping("/produit/delete/{id}")
    public void deletProduit(@PathVariable Long id, @RequestBody Produit produit){
         produitService.deleteProduit(id);
    }

//    //Create BonEntre
    @PostMapping("/bonentre")
    public BonEntre creerBonEntreAvecDetails(@RequestBody BonEntre bonEntre, @RequestBody List<DetailsEntre> detailsEntres) {
    return bonEntreService.creerBonEntreAvecDetails(bonEntre, detailsEntres);
}
//    //Liste Entre
    @GetMapping("/entre/all")
    public List<DetailsEntre> detailsEntreList(){
        return bonEntreService.allEntre();
    }
    //Liste Entre
//    @GetMapping("/entre/all")
//    public List<BonEntre> bonEntreList(){
//        return entreService.bonEntreList();
//    }
//    // Create
//    @PostMapping("/entre/create/{fournisseurId}/par/{managerId}")
//    public BonEntre createBonEntre(@PathVariable long fournisseurId, @PathVariable long managerId,
//                                   @RequestBody BonEntre bonEntre){
//        return entreService.createBonEntre(bonEntre, fournisseurId, managerId);
//    }

}
