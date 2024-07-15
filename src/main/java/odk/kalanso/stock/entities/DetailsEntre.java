package odk.kalanso.stock.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "bon_entre_id")
    @JsonBackReference
    private BonEntre bonEntre;
    @OneToOne
    private Produit produit;
//    @ManyToOne
//    private Motif motif;
}
