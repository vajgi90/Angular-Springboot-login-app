package hu.flowacademy.stockmarket.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "primaryExchange")
    private String primaryExchange;

    @Column(name = "calculationPrice")
    private String calculationPrice;

    @Column(name = "open")
    private Double open;

    @Column(name = "openTime")
    private Long openTime;

    @Column(name = "_close")
    private Double close;

    @Column(name = "closeTime")
    private Long closeTime;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Long low;

    @Column(name = "latestPrice")
    private Double latestPrice;

    @Column(name = "latestSource")
    private String latestSource;

    @Column(name = "latestTime")
    private String latestTime;

    @Column(name = "latestUpdate")
    private Long latestUpdate;

    private Long latestVolume;

    private Double iexRealtimePrice;

    private Long iexRealtimeSize;

    private Long iexLastUpdated;

    @Transient
    private Double delayedPrice;

    @Transient
    private Long delayedPriceTime;

    @Transient
    private Double extendedPrice;

    @Transient
    private Double extendedChange;

    @Transient
    private Double extendedChangePercent;

    @Transient
    private Long extendedPriceTime;

    private Double previousClose;

    private Long previousVolume;

    @Column(name = "_change")
    private Double change;

    private Double changePercent;

    private Long volume;


    private Double iexMarketPercent;

    private Long iexVolume;

    private Long avgTotalVolume;

    private Long iexBidPrice;

    private Long iexBidSize;

    private Long iexAskPrice;

    private Long iexAskSize;

    private Long marketCap;

    private Double peRatio;

    private Double week52High;

    private Double week52Low;

    private Double ytdChange;

    private Long lastTradeTime;

    private Boolean isUSMarketOpen;
}
