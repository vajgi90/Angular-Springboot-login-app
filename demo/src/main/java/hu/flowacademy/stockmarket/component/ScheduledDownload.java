package hu.flowacademy.stockmarket.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.flowacademy.stockmarket.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class ScheduledDownload {

    private String[] stockList = {"MSFT", "TSLA", "AMZN", "GOOGL","AAPL"};

    private final StockService stockService;


    public ScheduledDownload(StockService stockService) {
        this.stockService = stockService;
    }

    @Scheduled(fixedDelay = 600000, initialDelay = 10)
    public void downloadStocks() throws JsonProcessingException, JSONException {
        for (String x : stockList) {
            stockService.stockDownloader(x);
        }
    }

}
