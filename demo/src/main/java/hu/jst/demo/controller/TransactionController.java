package hu.jst.demo.controller;


import hu.jst.demo.entity.BuyingInputData;
import hu.jst.demo.entity.StockEntity;
import hu.jst.demo.entity.UserPortfolio;
import hu.jst.demo.service.UserPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
//@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    UserPortfolioService userPortfolioService;

    @PostMapping(value = "buy", consumes="application/json")
    public UserPortfolio createStock(@RequestBody BuyingInputData data) {
        return userPortfolioService.buyingStock(data.getEmail(), data.getAmount(), data.getSymbol());
    }

}
