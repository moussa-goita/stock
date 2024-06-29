package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Role;
import odk.kalanso.stock.entities.Vendeur;
import odk.kalanso.stock.repositories.RoleRepository;
import odk.kalanso.stock.repositories.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VendeurService {

    @Autowired
    private VendeurRepository vendeurRepository;
    @Autowired
    private RoleRepository roleRepository;

    //liste Produit
    public List<Vendeur> allVendeurs() {
        return  vendeurRepository.findAll();
    }
    //GET BY id
    public Optional<Vendeur> getVendeurById(Long id) {
        return vendeurRepository.findById(id);
    }
    //Create Vendeur
    public Vendeur addVendeur(Vendeur vendeur) {
        Role role = roleRepository.findByName("VENDEUR");
        vendeur.setRoles(Collections.singletonList(role));
        return  vendeurRepository.save(vendeur);
    }
    //Delete Vendeur
    public void deleteVendeur(){
        vendeurRepository.deleteAll();
    }
}
