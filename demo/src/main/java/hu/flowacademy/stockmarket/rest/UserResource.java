package hu.flowacademy.stockmarket.rest;

import hu.flowacademy.stockmarket.persistance.dto.UserRegister;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<User> user = userService.findOne(id);
        if(user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        UserRegister answer = userService.save(user);
        return new ResponseEntity(answer, HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

