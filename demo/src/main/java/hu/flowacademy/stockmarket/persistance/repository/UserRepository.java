package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

    void deleteByUsername(String username);

}
