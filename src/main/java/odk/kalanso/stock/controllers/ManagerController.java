package odk.kalanso.stock.controllers;

import odk.kalanso.stock.entities.Fournisseur;
import odk.kalanso.stock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private FournisseurService fournisseurService;

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

}
