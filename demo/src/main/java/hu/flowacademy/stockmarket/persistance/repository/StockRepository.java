package hu.flowacademy.stockmarket.persistance.repository;

import hu.flowacademy.stockmarket.persistance.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    public Optional<Stock> findFirstBySymbol(String symbol);

    public void deleteBySymbol (String symbol);


/*    @Transactional
    @Modifying
    @Query(value = "UPDATE stocks SET " +
            "change_ = ?2 ,change_percent = ?3,high = ?4,latest_trading_day = ?5, low = ?6, open_ = ?7, previous_close = ?8,price = ?9,volume = ?10 " +
            "WHERE symbol = ?1", nativeQuery = true)
    public void update(String symbol,
                       Double change,
                       String changePercent,
                       Double high,
                       LocalDate latestTradingDay,
                       Double low,
                       Double open,
                       Double previousClose,
                       Double price,
                       Integer volume
    );

    @Query(value = "SELECT EXISTS(SELECT * FROM stocks WHERE symbol = ?1)", nativeQuery = true)
    public int isExist(String symbol);

    @Query(value = "DELETE FROM stocks WHERE symbol = ?1", nativeQuery = true)
    public void deleteBySymbol (String symbol);*/
}
