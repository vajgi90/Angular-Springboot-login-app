package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByEmail(String email);

    List<Portfolio> findByStockSymbol(String symbol);

    @Query("SELECT p FROM  Portfolio p WHERE p.email=?1 AND p.isOpen=?2")
    List<Portfolio> findByEmailAndOpen(String email, boolean isOpen);

}
