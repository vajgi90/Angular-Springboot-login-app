package hu.jst.demo.entity;

public class BuyingInputData {
    private String email;
    private int amount;
    private String symbol;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSYMBOL(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "BuyingInputData{" +
                "email='" + email + '\'' +
                ", amount=" + amount +
                ", SYMBOL='" + symbol + '\'' +
                '}';
    }
}
