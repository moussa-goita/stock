package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.DetailsSortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsSortieRepository extends JpaRepository<DetailsSortie, Long> {
}
