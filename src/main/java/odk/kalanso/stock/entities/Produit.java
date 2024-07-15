package odk.kalanso.stock.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;
    String prix;
    String description;
    int prixVente;
    int qte;

    @ManyToOne
    private Category category;
    @OneToOne
    private Entrepot entrepot;

}
