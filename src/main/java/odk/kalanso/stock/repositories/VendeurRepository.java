package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendeurRepository extends JpaRepository<Vendeur, Long> {
}
