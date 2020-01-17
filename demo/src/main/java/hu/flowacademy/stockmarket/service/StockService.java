package hu.flowacademy.stockmarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.persistance.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StockService {

    @Autowired
    StockRepository stockRepository;

    //READ
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    //READ
    public Stock getSpecificStock(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    //CREATE
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    //DOWNLOAD METHOD
    public Stock stockDownloader(String symbol) throws JsonProcessingException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", "03db22b5camshd34fd82b1dad7a3p13f99cjsn5141407b69d2");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String resourceUrl = "https://alpha-vantage.p.rapidapi.com/query?symbol=" + symbol.toUpperCase() + "&function=GLOBAL_QUOTE";
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject obj = new JSONObject(response.getBody().toString());
            obj = obj.getJSONObject("Global Quote");
            Stock item = jsonHandler(obj);
            if (stockRepository.isExist(item.getSymbol()) == 0) {
                stockRepository.save(item);
            }
            stockRepository.update(item.getSymbol(), item.getChange(), item.getChangePercent(),
                    item.getHigh(), item.getLatestTradingDay(), item.getLow(), item.getOpen(), item.getPreviousClose(), item.getPrice(), item.getVolume());
            return item;
        }
        return null;
    }

    public Stock jsonHandler(JSONObject obj) {
        Stock item = new Stock(
                obj.optString("01. symbol"),
                obj.optString("02. open"),
                obj.optString("03. high"),
                obj.optString("04. low"),
                obj.optString("05. price"),
                obj.optString("06. volume"),
                obj.optString("07. latest trading day"),
                obj.optString("08. previous close"),
                obj.optString("09. change"),
                obj.optString("10. change percent"));
        return item;
    }

    //DELETE
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    //DELETE
    public void deleteStockBySymbol(String symbol) {
        stockRepository.deleteBySymbol(symbol);
    }

}
