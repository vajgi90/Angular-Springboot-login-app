package hu.flowacademy.stockmarket.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyingInputData {
    private String email;
    private int amount;
    private String symbol;
}
