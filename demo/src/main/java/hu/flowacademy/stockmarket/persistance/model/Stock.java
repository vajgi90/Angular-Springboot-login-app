package hu.flowacademy.stockmarket.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "open_")
    private Double open;

    @Column(name = "high")
    private Double high;


    @Column(name = "low")
    private Double low;

    @Column(name = "price")
    private Double price;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "latestTradingDay")
    private LocalDate latestTradingDay;

    @Column(name = "previousClose")
    private Double previousClose;

    @Column(name = "change_")
    private Double change;

    @Column(name = "changePercent")
    private String changePercent;

    private LocalDateTime savingTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOpen(String open) {
        this.open = Double.parseDouble(open);
    }

    public void setHigh(String high) {
        this.high = Double.parseDouble(high);
    }

    public void setLow(String low) {
        this.low = Double.parseDouble(low);
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public void setVolume(String volume) {
        this.volume = Integer.parseInt(volume);
    }

    public void setLatestTradingDay(String latestTradingDay) {
        this.latestTradingDay = LocalDate.parse(latestTradingDay);
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = Double.parseDouble(previousClose);
    }

    public void setChange(String change) {
        this.change = Double.parseDouble(change);
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public Stock(String symbol, String open, String high, String low, String price, String volume, String latestTradingDay, String previousClose, String change, String changePercent) {
        this.symbol = symbol;
        setOpen(open);
        setHigh(high);
        setLow(low);
        setPrice(price);
        setVolume(volume);
        setLatestTradingDay(latestTradingDay);
        setPreviousClose(previousClose);
        setChange(change);
        this.changePercent = changePercent;
        this.savingTime = LocalDateTime.now();
    }
}
