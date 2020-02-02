package hu.flowacademy.stockmarket.persistance.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stocksMonthly")
public class StockMonthly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String symbol;

    @Column(name = "date_")
    private String date;

    @Column(name = "open_")
    private Double open;

    private Double close;

    @Column(name = "high")
    private Double high;

    private Double low;

    private Integer volume;

    @JsonProperty("uOpen")
    private Double uOpen;

    @JsonProperty("uClose")
    private Double uClose;

    @JsonProperty("uHigh")
    private Double uHigh;

    @JsonProperty("uLow")
    private Double uLow;

    @JsonProperty("uVolume")
    private Double uVolume;

    @Column(name = "change_")
    private Double change;

    private Double changePercent;

    private String label;

    private Double changeOverTime;
}
