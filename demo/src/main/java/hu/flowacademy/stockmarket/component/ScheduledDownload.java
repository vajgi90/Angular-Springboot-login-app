package hu.flowacademy.stockmarket.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.flowacademy.stockmarket.service.StockService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledDownload {

    private String[] stockList = {"MSFT",  "TSLA", "AMZN", "GOOGL"};
/*            , "CMSCA", "ARQL", "LIFE", "MRLV", "BGCP", "MYL", "AMAT", "QCOM", "GILD", "BBBY", "HBAN", "NVDA",
            "EBAY", "ACOR", "NFLX", "GPOR", "SPPI", "SBUX", "AGNC", "ATVI", "TEAM", "OSTK", };*/

    @Autowired
    StockService stockService;

    @Scheduled(fixedDelay = 120000, initialDelay = 10000)
    public void downloadStocks() throws JsonProcessingException, JSONException {
        for (int i = 0; i < stockList.length; i++) {
            stockService.stockDownloader(stockList[i]);
            System.out.println(System.nanoTime());
        }
    }
}
