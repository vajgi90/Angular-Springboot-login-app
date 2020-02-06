package hu.flowacademy.stockmarket.rest;


import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import hu.flowacademy.stockmarket.persistance.model.StockMonthly;
import hu.flowacademy.stockmarket.service.StockMonthlyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StockMonthlyResource {

    private StockMonthlyService stockMonthlyService;

    @GetMapping("stockmonthly/{symbol}")
    public ResponseEntity<?> findAll(@PathVariable String symbol) {
        Optional<List<StockMonthly>> stockMonthly = stockMonthlyService.findAllBySymbol(symbol);
        return new ResponseEntity<>(stockMonthly, HttpStatus.OK);
    }

}
