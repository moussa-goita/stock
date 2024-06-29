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

    // Update Vendeur
    public Vendeur updateVendeur(Long id, Vendeur vendeur) {
        if (vendeurRepository.existsById(id)) {
            Vendeur existingVendeur = vendeurRepository.findById(id).orElse(null);
            if (existingVendeur != null) {
                if (vendeur.getUsername() != null && !vendeur.getUsername().equals(existingVendeur.getUsername())) {
                    existingVendeur.setUsername(vendeur.getUsername());
                }
                if (vendeur.getEmail() != null && !vendeur.getEmail().equals(existingVendeur.getEmail())) {
                    existingVendeur.setEmail(vendeur.getEmail());
                }
                if (vendeur.getPwd() != null && ! existingVendeur.getPwd().equals(existingVendeur.getPwd())) {
                    existingVendeur.setPwd(vendeur.getPwd());
                }
                return vendeurRepository.save(existingVendeur);
            }
        }
        return null;
    }

}
