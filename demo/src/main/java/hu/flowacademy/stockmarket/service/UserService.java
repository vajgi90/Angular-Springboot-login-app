package hu.flowacademy.stockmarket.service;

import hu.flowacademy.stockmarket.exception.ValidationException;
import hu.flowacademy.stockmarket.persistance.dto.UserModifyInput;
import hu.flowacademy.stockmarket.persistance.dto.UserModifyOutput;
import hu.flowacademy.stockmarket.persistance.dto.UserRegister;
import hu.flowacademy.stockmarket.persistance.dto.UserRegisterOutput;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findOneByEmail(String email) {
        return userRepository.findFirstByUsername(email);
    }

    public Optional<UserModifyOutput> findOneByEmailToModify(String email) {
        User user = userRepository.findFirstByUsername(email).orElseThrow(NoSuchElementException::new);
        UserModifyOutput userOut = new UserModifyOutput();
        userOut.setUsername(user.getUsername());
        userOut.setFirstName(user.getFirstName());
        userOut.setLastName(user.getLastName());
        userOut.setBirthdate(user.getDateOfBirth());
        userOut.setBudget(user.getBudget());
        return Optional.of(userOut);
    }

    public Optional<?> update(UserModifyInput input) {
        User user = userRepository.findFirstByUsername(input.getUsername()).orElseThrow(NoSuchElementException::new);
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setDateOfBirth(input.getBirthdate());
        return Optional.of(userRepository.save(user));
    }

    public UserRegisterOutput save(UserRegister userRegisterData) {
        UserRegisterOutput answer = new UserRegisterOutput();
        User user = new User();
        if (userRepository.findFirstByUsername(userRegisterData.getUsername()).isEmpty()) {
            user.setUsername(userRegisterData.getUsername());
            user.setPassword(passwordEncoder.encode(userRegisterData.getPassword()));
            user.setFirstName(userRegisterData.getFirstName());
            user.setLastName(userRegisterData.getLastName());
            user.setDateOfBirth(userRegisterData.getBirthdate());
            user.setRole(userRegisterData.getRole());
            user.setBudget(10000000l);
            userRepository.save(user);
            answer.setUsername(user.getUsername());
            answer.setRole(user.getRole());
            return answer;
        }
        throw new ValidationException("This username has been registered yet!");
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
