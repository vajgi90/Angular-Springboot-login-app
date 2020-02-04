package hu.flowacademy.stockmarket.rest;

import hu.flowacademy.stockmarket.persistance.dto.BuyingInputData;
import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PortfolioResource {

    private PortfolioService portfolioService;

    @GetMapping("portfolio/")
    public ResponseEntity<?> findAll(@RequestParam(value = "email") String email) {
        Optional<List<Portfolio>> portfolio = portfolioService.findAllByEmail(email);
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @GetMapping("portfolio/sort")
    public ResponseEntity<?> findAllByEmailAndStatus(@RequestParam(value = "email") String email, @RequestParam(value = "isOpen") boolean isOpen) {
        Optional<List<Portfolio>> portfolio = portfolioService.findByEmailAndOpen(email, isOpen);
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }



    @PostMapping(value = "portfolio/buy")
    public Optional<Portfolio> buyStock(@RequestBody BuyingInputData data) {
        return portfolioService.buyingStock(data.getEmail(), data.getAmount(), data.getSymbol());
    }

    @GetMapping(value = "portfolio/sell/{id}")
    public Optional<?> sellStock(@PathVariable Long id) {
        return portfolioService.sellingStock(id);
    }
}
