package odk.kalanso.stock.controllers;

import odk.kalanso.stock.dto.EntreDto;
import odk.kalanso.stock.entities.*;
import odk.kalanso.stock.services.*;
//import odk.kalanso.stock.services.EntreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private MotifService motifService;
    @Autowired
    private ProduitService produitService;

    @Autowired
    private BonEntreService bonEntreService;

    @Autowired
    private BonSortieService bonSortieService;
    @Autowired
    private CategoryService categoryService;
//    @Autowired
//    private EntreService entreService;
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
    public void deleteProduit(@PathVariable Long id, @RequestBody Produit produit){
         produitService.deleteProduit(id);
    }
    //Motif
    //Create Motif
    @PostMapping("motif/create")
    public Motif createMotif(@RequestBody Motif motif){
        return motifService.createMotif(motif);
    }
    //get by Id
    @GetMapping("motif/id")
    public Optional<Motif> getMotifById(int id){
        return motifService.getMotifById(id);
    }
    //List des motifs
    @PostMapping("motif/list")
    public List<Motif> listMotif(){
        return motifService.getAllMotifs();
    }
    //modifier Motif
    @PutMapping("motif/update/{id}")
    public Motif updateMotif(@PathVariable int id, @RequestBody Motif motif){
        return motifService.updateMotif(motif,id);
    }
    //Supprimer motif
    @DeleteMapping("motif/delete/{id}")
    public void deleteMotif(@PathVariable int id){
        motifService.deleteMotif(id);
    }
    //Category

    //Create Category
    @PostMapping("/category/create")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
    //get by Id
    @GetMapping("category/id")
    public Optional<Category> getCategoryById(int id){
        return categoryService.getCategoryById(id);
    }
    //List des categories
    @GetMapping("category/list")
    public List<Category> listCategory(){
        return categoryService.getAllCategories();
    }
    //update category
    @PutMapping("category/update/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category){
        return categoryService.updateCategory(category, id);
    }
    //suprimer Category
    @DeleteMapping("category/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
//    //Create BonEntre
    //tgest

    @PostMapping("creer")
    public ResponseEntity<BonEntre> createBonEntre(@RequestBody BonEntre bonEntre) {
        BonEntre createdBonEntre = bonEntreService.creeBonEntre(bonEntre);
        return new ResponseEntity<>(createdBonEntre, HttpStatus.CREATED);
    }
    // Endpoint pour récupérer les DetailsEntre d'un BonEntre par son ID
    @GetMapping("/{id}/details-entres")
    public ResponseEntity<List<DetailsEntre>> getDetailsEntresByBonEntreId(@PathVariable("id") Long bonEntreId) {
        List<DetailsEntre> detailsEntres = bonEntreService.getDetailsEntresByBonEntreId(bonEntreId);
        if (detailsEntres != null) {
            return ResponseEntity.ok(detailsEntres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Endpoint pour récupérer tous les BonEntre
    @GetMapping
    public List<BonEntre> getAllBonEntres() {
        return bonEntreService.getAllBonEntres();
    }

    // Endpoint pour récupérer un BonEntre par son ID
    @GetMapping("/entre/{id}")
    public ResponseEntity<BonEntre> getBonEntreById(@PathVariable("id") Long id) {
        Optional<BonEntre> bonEntreOptional = bonEntreService.getBonEntreById(id);
        return bonEntreOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Endpoint pour mettre à jour un BonEntre existant
    @PutMapping("/{id}")
    public ResponseEntity<BonEntre> updateBonEntre(@PathVariable("id") Long id, @RequestBody BonEntre bonEntre) {
        BonEntre updatedBonEntre = bonEntreService.updateBonEntre(id, bonEntre);
        if (updatedBonEntre != null) {
            return ResponseEntity.ok(updatedBonEntre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour supprimer un BonEntre par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonEntre(@PathVariable("id") Long id) {
        bonEntreService.deleteBonEntre(id);
        return ResponseEntity.noContent().build();
    }
    // Endpoint pour récupérer un DetailsEntre par son ID
    @GetMapping("/{id}")
    public ResponseEntity<DetailsEntre> getDetailsEntreById(@PathVariable("id") Long id) {
        Optional<DetailsEntre> detailsEntreOptional = bonEntreService.getDetailsEntreById(id);
        return detailsEntreOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //les Endpoint pour les sorties
    // pour récupérer tous les BonSorties
    @GetMapping("sortie/list")
    public ResponseEntity<List<BonSortie>> getAllBonSorties() {
        List<BonSortie> bonSorties = bonSortieService.getAllBonSorties();
        return ResponseEntity.ok(bonSorties);
    }

    //pour récupérer un BonSortie par son ID
    @GetMapping("/sortie/{id}")
    public ResponseEntity<BonSortie> getBonSortieById(@PathVariable Long id) {
        Optional<BonSortie> bonSortie = bonSortieService.getBonSortieById(id);
        return bonSortie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/bonSortie")
    public ResponseEntity<BonSortie> createBonSortie(@RequestBody BonSortie bonSortie) {
        try {
            BonSortie createdBonSortie = bonSortieService.createBonSortie(bonSortie);
            return new ResponseEntity<>(createdBonSortie, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/top-products-by-motif")
    public ResponseEntity<Map<String, Map<String, Integer>>> getTopProductsByMotif() {
        Map<String, Map<String, Integer>> topProductsByMotif = bonSortieService.getTopProductsByMotif();
        return ResponseEntity.ok(topProductsByMotif);
    }
    //pour créer un nouveau BonSortie
//    @PostMapping("sortie/create")
//    public ResponseEntity<BonSortie> createBonSortie(@RequestBody BonSortie bonSortie) {
//        BonSortie createdBonSortie = bonSortieService.createBonSortie(bonSortie);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdBonSortie);
//    }

    // pour mettre à jour un BonSortie existant par son ID
    @PutMapping("/sortie/update/{id}")
    public ResponseEntity<BonSortie> updateBonSortie(@PathVariable Long id, @RequestBody BonSortie bonSortie) {
        BonSortie updatedBonSortie = bonSortieService.updateBonSortie(id, bonSortie);
        if (updatedBonSortie != null) {
            return ResponseEntity.ok(updatedBonSortie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //pour supprimer un BonSortie par son ID
    @DeleteMapping("/sortie/delete/{id}")
    public ResponseEntity<Void> deleteBonSortie(@PathVariable Long id) {
        bonSortieService.deleteBonSortie(id);
        return ResponseEntity.noContent().build();
    }

    //pour récupérer les DetailsSortie d'un BonSortie par son ID
    @GetMapping("sortie/{id}/detailssorties")
    public ResponseEntity<List<DetailsSortie>> getDetailsSortiesByBonSortieId(@PathVariable Long id) {
        List<DetailsSortie> detailsSorties = bonSortieService.getDetailsSortiesByBonSortieId(id);
        return ResponseEntity.ok(detailsSorties);
    }

    // pour récupérer un DetailsSortie par son ID
//  @GetMapping("sortie/detailssorties/{id}")
//    public ResponseEntity<DetailsSortie> getDetailsSortieById(@PathVariable Long id) {
//        Optional<DetailsSortie> detailsSortie = detailsSortieService.getDetailsSortieById(id);
//        return detailsSortie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
}


