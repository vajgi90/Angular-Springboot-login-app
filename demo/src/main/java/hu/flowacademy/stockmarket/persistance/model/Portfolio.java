package hu.flowacademy.stockmarket.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String email;
    private String stockSymbol;
    private int amount;
    private LocalDateTime buyingTime;
    private LocalDateTime sellingTime;
    private int buyingPricePerStock;
    private int sellingPricePerStock;
    private int buyingPrice;
    private int sellingPrice;
    private int priceDifference;
    private int priceDifferencePerStock;
    private boolean isOpen;
}
