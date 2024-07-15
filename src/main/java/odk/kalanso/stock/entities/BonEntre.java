package odk.kalanso.stock.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Data
public class BonEntre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date DateCommande;
    String Statut;
    @OneToMany(mappedBy = "bonEntre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetailsEntre> detailsEntres;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Fournisseur fournisseur;
}
