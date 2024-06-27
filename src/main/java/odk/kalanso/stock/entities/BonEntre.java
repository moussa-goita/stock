package odk.kalanso.stock.entities;

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
    @OneToMany
    private List<DetailsEntre> detailsEntres;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Fournisseur fournisseur;
}
