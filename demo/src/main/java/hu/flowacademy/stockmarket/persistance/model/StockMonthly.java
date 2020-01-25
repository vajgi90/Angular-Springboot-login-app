package hu.flowacademy.stockmarket.persistance.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "date_")
    private String date;

    @Column(name = "open_")
    private Double open;

    private Double close;

    @Column(name = "high")
    private Double high;

    private Double low;

    private Integer volume;

    private Double uOpen;

    private Double uClose;

    private Double uHigh;

    private Double uLow;

    private Double uVolume;

    @Column(name = "change_")
    private Double change;

    private Double changePercent;

    private String label;

    private Double changeOverTime;
}
