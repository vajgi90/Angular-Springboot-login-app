package hu.flowacademy.stockmarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.flowacademy.stockmarket.persistance.model.StockMonthly;
import hu.flowacademy.stockmarket.persistance.repository.StockMonthlyRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class StockMonthlyService {

    private final StockMonthlyRepository stockMonthlyRepository;

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    private final Gson gson;

    private final ObjectMapper objectMapper;

    public Optional<List<StockMonthly>> findAllBySymbol(String symbol) {
        return Optional.of(stockMonthlyRepository.findBySymbol(symbol));
    }

    public List<StockMonthly> stockMonthlyDownloader(String symbol) throws JsonProcessingException, JSONException {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String resourceUrl = "https://sandbox.iexapis.com/stable/stock/" + symbol.toUpperCase() + "/chart/1m?token=Tpk_634a471be2db41a096a3488d074c24a3";
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
        JSONArray obj = new JSONArray(response.getBody().toString());
        log.info(obj.toString());
        Type listType = new TypeToken<ArrayList<StockMonthly>>(){}.getType();
//        ArrayList<StockMonthly> list = gson.fromJson(obj.toString(), listType);
        //Temporary comment below until I find solution to replace Gson
        List<StockMonthly> list = Arrays.asList(objectMapper.readValue(obj.toString(), StockMonthly[].class));
        list.forEach(x -> x.setSymbol(symbol));
        return stockMonthlyRepository.saveAll(list);
    }
}
