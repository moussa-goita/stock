package odk.kalanso.stock.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DetailsSortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String prixTotal;
    String qte;
    Date dateExpire;

    @ManyToOne
    private  Produit produit;

    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private BonSortie bonSortie;

}
