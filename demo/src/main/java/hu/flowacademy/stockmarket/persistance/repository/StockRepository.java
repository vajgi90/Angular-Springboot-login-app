package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findFirstBySymbolOrderByIdDesc(String symbol);

    void deleteBySymbol(String symbol);

    @Query("select s from Stock s where s.symbol = ?1 order by s.id desc")
    Optional<List<Stock>> findAllBySymbol(String symbol);

}
