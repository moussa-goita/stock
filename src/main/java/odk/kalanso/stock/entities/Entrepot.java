package odk.kalanso.stock.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Entrepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lieu;
    @ManyToOne
    private Admin admin;
    @OneToOne
    private Manager manager;
    @OneToMany
    private List<Vendeur> vendeurs;
}
