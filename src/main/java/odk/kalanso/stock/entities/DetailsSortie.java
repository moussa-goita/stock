package odk.kalanso.stock.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne
    private  Produit produit;

    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    @JsonBackReference
    private BonSortie bonSortie;


}
