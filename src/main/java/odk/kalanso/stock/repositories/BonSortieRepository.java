package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.BonSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonSortieRepository extends JpaRepository<BonSortie, Long> {
}
