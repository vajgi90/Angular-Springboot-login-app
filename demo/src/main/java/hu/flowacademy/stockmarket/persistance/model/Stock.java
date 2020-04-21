package hu.flowacademy.stockmarket.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Transient
    private Double iexRealtimePrice;

/*    @JsonIgnore
    @Transient*/
    private Long iexRealtimeSize;

/*    @JsonIgnore
    @Transient*/
    private Long iexLastUpdated;

/*    @JsonIgnore
    @Transient*/
    private Double delayedPrice;

/*    @JsonIgnore
    @Transient*/
    private Long delayedPriceTime;

/*    @JsonIgnore
    @Transient*/
    private Double extendedPrice;

/*    @JsonIgnore
    @Transient*/
    private Double extendedChange;

/*    @JsonIgnore
    @Transient*/
    private Double extendedChangePercent;

/*    @JsonIgnore
    @Transient*/
    private Long extendedPriceTime;

    private Double previousClose;

    private Long previousVolume;

    @Column(name = "_change")
    private Double change;

    private Double changePercent;

    private Long volume;

/*    @JsonIgnore
    @Transient*/
    private Double iexMarketPercent;

/*    @JsonIgnore
    @Transient*/
    private Long iexVolume;

    private Long avgTotalVolume;

/*    @JsonIgnore
    @Transient*/
    private Long iexBidPrice;

/*    @JsonIgnore
    @Transient*/
    private Long iexBidSize;

/*    @JsonIgnore
    @Transient*/
    private Long iexAskPrice;

/*    @JsonIgnore
    @Transient*/
    private Long iexAskSize;

    private Long marketCap;

    private Double peRatio;

    private Double week52High;

    private Double week52Low;

    private Double ytdChange;

    private Long lastTradeTime;

    private Boolean isUSMarketOpen;
}
