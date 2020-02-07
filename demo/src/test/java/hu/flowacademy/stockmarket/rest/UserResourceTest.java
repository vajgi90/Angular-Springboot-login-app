/*
package hu.flowacademy.stockmarket.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.is;


@WebMvcTest(controllers = {UserResource.class})
@RunWith(SpringRunner.class)
public class UserResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
    @MockBean
    private BCryptPasswordEncoder encoder;

    @Test
    public void findAllTest() throws Exception {

        List<User> users = new ArrayList<>();
        User user1 = new User(1l, "vajgi90@gmail.com", "tester", "Thomas", "Smith", LocalDate.of(1990,11,07), 100000l, Role.USER);
        users.add(user1);

        given(userService.findAll())
                .willReturn(users);

        mvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"username\": \"vajgi90@gmail.com\",\n" +
                        "        \"password\": \"tester\",\n" +
                        "        \"firstName\": \"Thomas\",\n" +
                        "        \"lastName\": \"Smith\",\n" +
                        "        \"dateOfBirth\": \"1990-11-07\",\n" +
                        "        \"budget\": 100000,\n" +
                        "        \"role\": \"USER\",\n" +
                        "        \"authorities\": [\n" +
                        "            {\n" +
                        "                \"authority\": \"USER\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "]"));

    }
}
*/
