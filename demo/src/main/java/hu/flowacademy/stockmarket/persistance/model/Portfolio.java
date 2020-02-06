package hu.flowacademy.stockmarket.persistance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyingTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sellingTime;

    private Long buyingPricePerStock;
    private Long sellingPricePerStock;
    private Long buyingPrice;
    private Long sellingPrice;
    private Long priceDifference;
    private Long priceDifferencePerStock;
    private boolean isOpen;

    public Portfolio(String email, String stockSymbol, int amount, long buyingPricePerStock) {
        this.email = email;
        this.stockSymbol = stockSymbol;
        this.amount = amount;
        this.buyingTime = LocalDateTime.now();
        this.buyingPricePerStock = buyingPricePerStock;
        this.buyingPrice = this.amount * this.buyingPricePerStock;
        this.isOpen = true;
    }
}
