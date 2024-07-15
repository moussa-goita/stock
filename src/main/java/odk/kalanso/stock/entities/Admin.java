package odk.kalanso.stock.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin extends  User{

    @OneToMany
    private List<Entrepot> entrepotList;
//    @OneToMany
//    private List<BonSortie> bonSorties;
//    @OneToMany
//    private List<BonEntre> bonEntres;

}
