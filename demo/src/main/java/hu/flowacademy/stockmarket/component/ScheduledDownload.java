package hu.flowacademy.stockmarket.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.persistance.model.StockSymbol;
import hu.flowacademy.stockmarket.service.PortfolioService;
import hu.flowacademy.stockmarket.service.StockMonthlyService;
import hu.flowacademy.stockmarket.service.StockService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class ScheduledDownload {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockMonthlyService stockMonthlyService;



    @Scheduled(fixedDelay = 10000, initialDelay = 10)
    public void downloadStocks() throws JsonProcessingException, JSONException {
        for (StockSymbol x : StockSymbol.values()) {
            stockService.stockDownloader(x.toString());
        }
    }

    @Scheduled(fixedDelay = 7200000, initialDelay = 10)
    public void downloadMonthlyStocks() throws JsonProcessingException, JSONException {
        for (StockSymbol x : StockSymbol.values()) {
            stockMonthlyService.stockMonthlyDownloader(x.toString());
        }
    }

}


