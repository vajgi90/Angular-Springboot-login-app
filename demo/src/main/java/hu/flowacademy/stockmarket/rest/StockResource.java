package hu.flowacademy.stockmarket.rest;

import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.service.StockMonthlyService;
import hu.flowacademy.stockmarket.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StockResource {


    private final StockService stockService;

    private final StockMonthlyService stockMonthlyService;

    @GetMapping("stocks")
    public Collection<Stock> americanStocks() {
        return stockService.getStocks().stream()
                .collect(Collectors.toList());
    }

    @GetMapping ("stocks/{name}")
    public Optional<List<Stock>> searchStocksByName(@PathVariable(value = "name") String name) {
        return stockService.getAllBySymbol(name);
    }

    @GetMapping ("stock/{name}")
    public Optional<Stock> searchOneStockByName(@PathVariable(value = "name") String name) {
        return stockService.getSpecificStock(name);
    }

    @DeleteMapping(value = "stocks/{symbol}")
    public void deleteUser(@PathVariable String symbol) {
        stockService.deleteStockBySymbol(symbol);
    }
}
