package hu.flowacademy.stockmarket.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.persistance.model.StockMonthly;
import hu.flowacademy.stockmarket.service.StockMonthlyService;
import hu.flowacademy.stockmarket.service.StockService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StockResource {

    @Autowired
    StockService stockService;

    @Autowired
    StockMonthlyService stockMonthlyService;

    //Get every stocks from the database
    @GetMapping("stocks")
    //@CrossOrigin(origins = "http://localhost:4200")
    public Collection<Stock> americanStocks() {
        return stockService.getStocks().stream()
                .collect(Collectors.toList());
    }

    @GetMapping ("stocks3/{name}")
    public String searchMonthly(@PathVariable(value = "name") String name) throws JsonProcessingException, JSONException {
        return stockMonthlyService.stockMonthlyDownloader(name).toString();
    }

    @GetMapping ("stocks/{name}")
    public Stock searchStocksByName(@PathVariable(value = "name") String name) {
        return stockService.getSpecificStock(name);
    }

    //Get specific stock by symbol
    //Symbols for download (TSLA - Tesla, MSFT - Microsoft, AAPL - Apple, GOOGL - Google, AMZN - Amazon)
    @GetMapping ("stocks/download/{symbol}")
    public Stock downloadStockData(@PathVariable String symbol) throws JsonProcessingException, JSONException {
        Stock item = stockService.stockDownloader(symbol);
        return item;
    }

    @DeleteMapping(value = "stock/{symbol}")
    public void deleteUser(@PathVariable String symbol) {
        stockService.deleteStockBySymbol(symbol);
    }

}
