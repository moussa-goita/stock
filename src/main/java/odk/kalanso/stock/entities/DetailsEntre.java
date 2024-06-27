package odk.kalanso.stock.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetailsEntre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String prix;
    int qte;
    @ManyToOne
    private BonEntre bonEntre;
    @ManyToOne
    private  Produit produit;
}
