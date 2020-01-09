package hu.jst.demo.service;

import hu.jst.demo.entity.StockEntity;
import hu.jst.demo.entity.User;
import hu.jst.demo.entity.UserPortfolio;
import hu.jst.demo.repository.UserPortfolioRepository;
import hu.jst.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

@Service
public class UserPortfolioService {

    @Autowired
    UserPortfolioRepository userPortfolioRepository;

    @Autowired
    UserService userService;

    @Autowired
    StockService stockService;

    public UserPortfolio buyingStock(String email, int amount, String symbol) {
        StockEntity stock = stockService.getSpecificStock(symbol);
        User user = userService.getSpecificUser(email);
        long budget = user.getBudget();
        double temp = stock.getPrice() * 100;
        int buyingPricePerStock = (int) temp;
        if(user.getBudget() >= stock.getPrice()) {
            UserPortfolio transaction = new UserPortfolio(
                    email, symbol,amount,buyingPricePerStock);
            long newBudget = budget - amount * buyingPricePerStock;
            user.setBudget(newBudget);
            userPortfolioRepository.save(transaction);
            return transaction;
        }
        return null;
    }

    public StockEntity  testStock(String email, int amount, String symbol) {
        StockEntity stock = stockService.getSpecificStock(symbol);
        User user = userService.getSpecificUser(email);
        return stock;
    }
}
