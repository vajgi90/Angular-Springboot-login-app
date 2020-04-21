/*package hu.flowacademy.stockmarket.rest;

import com.google.gson.Gson;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTestIntegration {

    private static User u1;
    private static User u2;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        u1 = new User(1l, "vajgi90@gmail.com", "psd", "Thomas", "Smith", LocalDate.of(1990,11,07), 100000l, Role.USER);
        u2 = new User(2l, "tester@gmail.com", "test", "Tester", "Bill", LocalDate.of(1991,11,07), 100000l,Role.USER);
        Gson g = new Gson();
    }

    @Test
    public void findAllTestIT() {

        ResponseEntity<User[]> result= this.restTemplate
                .getForEntity("http://127.0.0.1:"+port+"/api/users", User[].class);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), is(notNullValue()));
    }
}*/
