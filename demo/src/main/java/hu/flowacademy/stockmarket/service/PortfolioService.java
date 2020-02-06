package hu.flowacademy.stockmarket.service;


import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import hu.flowacademy.stockmarket.persistance.model.Stock;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PortfolioService {

    private PortfolioRepository portfolioRepository;

    private UserService userService;

    private StockService stockService;

    public Optional<List<Portfolio>> findAllByEmail(String email) {
        List<Portfolio> list = portfolioRepository.findByEmail(email);
        for (Portfolio pf: list) {
            Stock stock = stockService.getSpecificStock(pf.getStockSymbol()).orElseThrow();
            Double temp = stock.getLatestPrice() * 100;
            Long currentPricePerStock = temp.longValue();
            pf.setPriceDifferencePerStock(currentPricePerStock - pf.getBuyingPricePerStock());
            pf.setPriceDifference((currentPricePerStock * pf.getAmount())-pf.getBuyingPrice());
        }
        return Optional.of(list);
    }

    public Optional<List<Portfolio>> findByEmailAndClosed(String email, boolean isOpen) {
/*        List<Portfolio> list = portfolioRepository.findByEmail(email);
        for (Portfolio pf: list) {
            if(pf.isOpen()) {
                Stock stock = stockService.getSpecificStock(pf.getStockSymbol()).orElseThrow();
                Double temp = stock.getLatestPrice() * 100;
                Long currentPricePerStock = temp.longValue();
                pf.setPriceDifferencePerStock(currentPricePerStock - pf.getBuyingPricePerStock());
                pf.setPriceDifference((currentPricePerStock * pf.getAmount()) - pf.getBuyingPrice());
            }
        }*/
        return Optional.of(portfolioRepository.findByEmailAndOpen(email, isOpen));
    }

    public Optional<List<Portfolio>> findByEmailAndOpen(String email, boolean isOpen) {
        return Optional.of(
                portfolioRepository.findByEmailAndOpen(email, isOpen).stream().filter(x -> x.isOpen()).peek(pf -> {
                    Stock stock = stockService.getSpecificStock(pf.getStockSymbol()).orElseThrow();
                    Double temp = stock.getLatestPrice() * 100;
                    Long currentPricePerStock = temp.longValue();
                    pf.setPriceDifferencePerStock(currentPricePerStock - pf.getBuyingPricePerStock());
                    pf.setPriceDifference((currentPricePerStock * pf.getAmount()) - pf.getBuyingPrice());
                }).collect(Collectors.toList())
        );
    }

    public List<Portfolio> findBySymbol(String symbol) {
        return portfolioRepository.findByStockSymbol(symbol);
    }

    public Optional<Portfolio> buyingStock(String email, int amount, String symbol) {
        Stock stock = stockService.getSpecificStock(symbol).orElseThrow();
        log.info(String.valueOf(stock));
        User user = userService.findOneByEmail(email).orElseThrow();
        log.info(String.valueOf(user));
        Long budget = user.getBudget();
        Double temp = stock.getLatestPrice() * 100;
        Long buyingPricePerStock = temp.longValue();
        log.info(String.valueOf(buyingPricePerStock * amount));
        if(user.getBudget() >= (buyingPricePerStock * amount)) {
            Portfolio transaction = new Portfolio (
                    email, symbol,amount, buyingPricePerStock);
            long newBudget = budget - amount * buyingPricePerStock;
            user.setBudget(newBudget);
            portfolioRepository.save(transaction);
            return Optional.of(transaction);
        } else {
            return Optional.empty();
        }
    }

    public Optional<?> sellingStock(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow();
        if(portfolio.isOpen()) {
            User user = userService.findOneByEmail(portfolio.getEmail()).orElseThrow();
            Stock stock = stockService.getSpecificStock(portfolio.getStockSymbol()).orElseThrow();
            Double temp = stock.getLatestPrice() * 100;
            Long sellingPricePerStock = temp.longValue();
            portfolio.setSellingPrice(sellingPricePerStock * portfolio.getAmount());
            portfolio.setSellingPricePerStock(sellingPricePerStock);
            portfolio.setSellingTime(LocalDateTime.now());
            portfolio.setPriceDifference((sellingPricePerStock * portfolio.getAmount()) - portfolio.getBuyingPrice());
            portfolio.setPriceDifferencePerStock(sellingPricePerStock - portfolio.getBuyingPricePerStock());
            portfolio.setOpen(false);
            user.setBudget(user.getBudget() + portfolio.getSellingPrice());
            return Optional.of(portfolio);
        }
        return Optional.of("This portfolio was closed");
    }

}
