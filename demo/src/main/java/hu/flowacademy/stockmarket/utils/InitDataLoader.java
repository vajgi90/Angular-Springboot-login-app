package hu.flowacademy.stockmarket.utils;

import hu.flowacademy.stockmarket.persistance.model.Portfolio;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.PortfolioRepository;
import hu.flowacademy.stockmarket.persistance.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Transactional
@AllArgsConstructor
public class InitDataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PortfolioRepository portfolioRepository;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().username("tester@gmail.com").password(passwordEncoder.encode("tester")).budget(10000000l).role(Role.USER).build());
        userRepository.save(User.builder().username("vajgi90@gmail.com").password(passwordEncoder.encode("123")).role(Role.ADMIN).build());
        portfolioRepository.save(Portfolio.builder().id(1l).email("tester@gmail.com").stockSymbol("TSLA").amount(10).isOpen(true).buyingPrice(6000l).buyingPricePerStock(600l).buyingTime(LocalDateTime.now()).build());
       /* portfolioRepository.save(Portfolio.builder().email("tester@gmail.com").stockSymbol("MSFT").amount(1).isOpen(false).buyingPrice(6000l).buyingPricePerStock(600l).buyingTime(LocalDateTime.now()).build());
        portfolioRepository.save(Portfolio.builder().email("tester@gmail.com").stockSymbol("FB").amount(1).isOpen(false).buyingPrice(6000l).buyingPricePerStock(600l).buyingTime(LocalDateTime.now()).build());*/
    }


}
