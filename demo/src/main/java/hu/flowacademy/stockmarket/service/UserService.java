package hu.flowacademy.stockmarket.service;

import hu.flowacademy.stockmarket.persistance.dto.UserRegister;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
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

    public UserRegister save(User user) {
        UserRegister answer = new UserRegister();
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            User oldUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            user.setPassword(oldUser.getPassword());
        }
        User savedUser = userRepository.save(user);
        answer.setUsername(savedUser.getUsername());
        if(user.getRole().equals(Role.USER)) {
            answer.setRole(savedUser.getRole());
        } else {
            answer.setRole(Role.ADMIN);
        }
        return answer;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
