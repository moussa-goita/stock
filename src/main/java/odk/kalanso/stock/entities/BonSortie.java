package odk.kalanso.stock.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    Date dateSortie;
    @OneToMany
    @JsonManagedReference
    private List<DetailsSortie> detailsSorties;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Notification notification;
    @ManyToOne
    private Motif motif;
}
