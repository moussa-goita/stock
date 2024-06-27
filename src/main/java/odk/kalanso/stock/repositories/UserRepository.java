package odk.kalanso.stock.repositories;

import odk.kalanso.stock.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserRepository extends JpaRepository<User long> {
}
