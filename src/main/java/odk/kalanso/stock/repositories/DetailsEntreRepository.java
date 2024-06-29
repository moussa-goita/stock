package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.DetailsEntre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsEntreRepository extends JpaRepository<DetailsEntre,Long> {
}
