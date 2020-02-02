package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    public List<Portfolio> findByEmail(String email);

    public List<Portfolio> findByStockSymbol(String symbol);

}
