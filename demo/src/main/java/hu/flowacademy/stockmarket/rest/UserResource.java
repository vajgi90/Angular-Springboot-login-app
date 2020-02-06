package hu.flowacademy.stockmarket.rest;

import hu.flowacademy.stockmarket.persistance.dto.UserModifyInput;
import hu.flowacademy.stockmarket.persistance.dto.UserModifyOutput;
import hu.flowacademy.stockmarket.persistance.dto.UserRegister;
import hu.flowacademy.stockmarket.persistance.dto.UserRegisterOutput;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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


    @GetMapping("/user")
    public ResponseEntity<?> findOne(@RequestParam(value = "email") String email) {
        Optional<UserModifyOutput> user = userService.findOneByEmailToModify(email);
        if(user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }


    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody UserRegister user) {
        UserRegisterOutput answer = userService.save(user);
        return new ResponseEntity(answer, HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateOne(@RequestParam(value = "email") String email, @RequestBody UserModifyInput input) {
        userService.update(input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> create(@RequestParam(value = "email") String email) {
        User user = userService.findOneByEmail(email).orElseThrow(NoSuchElementException::new);
        return ResponseEntity.noContent().build();
    }

}

