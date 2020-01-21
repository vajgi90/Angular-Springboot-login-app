package hu.flowacademy.stockmarket.utils;

import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
@Transactional
@AllArgsConstructor
public class InitDataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder().username("gez@gmail.com").password(passwordEncoder.encode("pass")).firstName("Géza").lastName("Kovács").dateOfBirth(LocalDate.of(1990,01,01)).role(Role.USER).build());
        userRepository.save(User.builder().username("vajgi90@gmail.com").password(passwordEncoder.encode("123")).firstName("Tamás").lastName("Vajgely").dateOfBirth(LocalDate.of(1990,11,07)).role(Role.ADMIN).build());
    }

}
