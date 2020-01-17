package hu.flowacademy.stockmarket.rest;

import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}

