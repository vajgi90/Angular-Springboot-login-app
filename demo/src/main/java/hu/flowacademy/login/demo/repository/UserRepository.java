package hu.flowacademy.login.demo.repository;

import hu.flowacademy.login.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    public List<User> findAll();
}
