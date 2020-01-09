package hu.jst.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="users_portfolio")
public class UserPortfolio {

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

    private UserPortfolio() {}

    public UserPortfolio(String email, String stockSymbol, int amount, int buyingPricePerStock) {
        this.email = email;
        this.stockSymbol = stockSymbol;
        this.amount = amount;
        this.buyingTime = LocalDateTime.now();
        this.buyingPricePerStock = buyingPricePerStock;
        this.buyingPrice = this.amount * this.buyingPricePerStock;
        this.isOpen = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(LocalDateTime buyingTime) {
        this.buyingTime = buyingTime;
    }

    public LocalDateTime getSellingTime() {
        return sellingTime;
    }

    public void setSellingTime(LocalDateTime sellingTime) {
        this.sellingTime = sellingTime;
    }

    public int getBuyingPricePerStock() {
        return buyingPricePerStock;
    }

    public void setBuyingPricePerStock(int buyingPricePerStock) {
        this.buyingPricePerStock = buyingPricePerStock;
    }

    public int getSellingPricePerStock() {
        return sellingPricePerStock;
    }

    public void setSellingPricePerStock(int sellingPricePerStock) {
        this.sellingPricePerStock = sellingPricePerStock;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(int priceDifference) {
        this.priceDifference = priceDifference;
    }

    public int getPriceDifferencePerStock() {
        return priceDifferencePerStock;
    }

    public void setPriceDifferencePerStock(int priceDifferencePerStock) {
        this.priceDifferencePerStock = priceDifferencePerStock;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "UsersPortfolio{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", amount=" + amount +
                ", buyingTime=" + buyingTime +
                ", sellingTime=" + sellingTime +
                ", buyingPricePerStock=" + buyingPricePerStock +
                ", sellingPricePerStock=" + sellingPricePerStock +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                ", priceDifference=" + priceDifference +
                ", priceDifferencePerStock=" + priceDifferencePerStock +
                ", isOpen=" + isOpen +
                '}';
    }
}
