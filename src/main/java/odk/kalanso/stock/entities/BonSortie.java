package odk.kalanso.stock.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class BonSortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String motif;
    Date dateSortie;
    @OneToMany
    private List<DetailsSortie> detailsSorties;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
}
