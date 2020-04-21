package hu.flowacademy.stockmarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.persistance.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final ObjectMapper mapper;

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;


    //READ
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    //READ
    public Optional<List<Stock>> getAllBySymbol(String symbol) {
        return stockRepository.findAllBySymbol(symbol);
    }

    //READ
    public Optional<Stock> getSpecificStock(String symbol) {
        return stockRepository.findFirstBySymbolOrderByIdDesc(symbol);
    }

    //CREATE
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock stockDownloader(String symbol) throws JsonProcessingException, JSONException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String resourceUrl = "https://sandbox.iexapis.com/stable/stock/" + symbol.toUpperCase() + "/quote?token=Tpk_634a471be2db41a096a3488d074c24a3";
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
        JSONObject obj = new JSONObject(response.getBody().toString());
        Stock stock = mapper.readValue(obj.toString(), Stock.class);
        stock.setDownloadTime(LocalDateTime.now());
        return stockRepository.save(stock);
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
