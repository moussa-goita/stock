package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
