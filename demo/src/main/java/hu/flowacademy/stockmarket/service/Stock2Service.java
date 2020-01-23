package hu.flowacademy.stockmarket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import hu.flowacademy.stockmarket.persistance.model.Stock2;
import hu.flowacademy.stockmarket.persistance.repository.Stock2Repository;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class Stock2Service {

    private final Stock2Repository stock2Repository;

    public Stock2 stockDownloader2(String symbol) throws JsonProcessingException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String resourceUrl = "https://sandbox.iexapis.com/stable/stock/" + symbol.toUpperCase() + "/quote?token=Tpk_634a471be2db41a096a3488d074c24a3";
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
        JSONObject obj = new JSONObject(response.getBody().toString());
        ObjectMapper MAPPER = new ObjectMapper();
        Stock2 stock = MAPPER.readValue(obj.toString(), Stock2.class);
        stock2Repository.save(stock);
        return stock;
    }

}
