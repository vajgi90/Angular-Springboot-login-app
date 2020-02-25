package hu.flowacademy.stockmarket.persistance.dto;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInputData {
    private String email;
    private int amount;
    private String symbol;
}
