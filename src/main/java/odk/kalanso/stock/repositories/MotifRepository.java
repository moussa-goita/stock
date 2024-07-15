package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository extends JpaRepository<Motif, Integer> {
}
