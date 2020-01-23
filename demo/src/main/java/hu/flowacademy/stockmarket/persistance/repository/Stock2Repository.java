package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.Stock2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Stock2Repository extends JpaRepository<Stock2, Long> {

    public Optional<Stock2> findFirstBySymbol(String symbol);

    public void deleteBySymbol (String symbol);
}
