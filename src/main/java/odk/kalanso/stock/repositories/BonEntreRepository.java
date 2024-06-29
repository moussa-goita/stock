package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.BonEntre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonEntreRepository extends JpaRepository<BonEntre, Long> {
}
