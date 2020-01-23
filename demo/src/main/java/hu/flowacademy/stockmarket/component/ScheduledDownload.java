package hu.flowacademy.stockmarket.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.tools.jconsole.JConsoleContext;
import hu.flowacademy.stockmarket.persistance.model.Stock2;
import hu.flowacademy.stockmarket.service.Stock2Service;
import hu.flowacademy.stockmarket.service.StockService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledDownload {

    private String[] stockList = {"MSFT"}; /*"TSLA", "AMZN", "GOOGL","AAPL"};*/

    @Autowired
    Stock2Service stock2Service;

/*    @Scheduled(fixedDelay = 120000, initialDelay = 10000)
    public void downloadStocks() throws JsonProcessingException, JSONException {
        for (int i = 0; i < stockList.length; i++) {
            stockService.stockDownloader(stockList[i]);
            System.out.println(System.nanoTime());
        }
    }*/

    @Scheduled(fixedDelay = 600000, initialDelay = 10)
    public void downloadStocks() throws JsonProcessingException, JSONException {
        for (int i = 0; i < stockList.length; i++) {
            var s = stock2Service.stockDownloader2(stockList[i]);
            System.out.println(s);
        }
    }
}
