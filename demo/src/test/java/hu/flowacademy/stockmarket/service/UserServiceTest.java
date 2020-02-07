package hu.flowacademy.stockmarket.service;


import hu.flowacademy.stockmarket.persistance.dto.UserModifyOutput;
import hu.flowacademy.stockmarket.persistance.model.Role;
import hu.flowacademy.stockmarket.persistance.model.User;
import hu.flowacademy.stockmarket.persistance.repository.UserRepository;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
    private static User u1;
    private static User u2;
    private static UserModifyOutput um1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        u1 = new User(1l, "vajgi90@gmail.com", "psd", "Thomas", "Smith", LocalDate.of(1990,11,07), 100000l,Role.USER);
        u2 = new User(2l, "tester@gmail.com", "test", "Tester", "Bill", LocalDate.of(1991,11,07), 100000l,Role.USER);
        um1 = new UserModifyOutput("vajgi90@gmailcom", "Thomas", "Smith", LocalDate.of(1990,11,07), 100000l);

    }

    @Test
    public void findAllTest_WhenRecordNotExist() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(userRepository.findAll().size(), is(0));
        verify(userRepository, times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecordsExist() {

        when(userRepository.findAll()).thenReturn(Arrays.asList(u1, u2));
        assertThat(userRepository.findAll().size(), is(2));
        assertThat(userRepository.findAll().get(0), is(u1));
        assertThat(userRepository.findAll().get(1),is(u2));
        verify(userRepository, times(3)).findAll();

    }

    @Test
    public void findOneByEmailTest() {

        when(userRepository.findFirstByUsername("vajgi90@gmail.com")).thenReturn(Optional.of(u1));
        assertThat(userRepository.findFirstByUsername("vajgi90@gmail.com"), is(Optional.of(u1)));
        verify(userRepository, times(1)).findFirstByUsername("vajgi90@gmail.com");
    }

    @Test
    public void findOneByEmailToModifyTest() {
        //when(userService.findOneByEmailToModify("vajgi90@gmail.com")).thenReturn(Optional.of(um1));
        //assertThat(userService.findOneByEmailToModify("vajgi90@gmail.com"), is(Optional.of(um1)));
        UserModifyOutput um2 = userService.findOneByEmailToModify("vajgi90@gmail.com").orElseThrow();
        assertEquals(um2.getUsername(), u1.getUsername());
        assertEquals(um2.getFirstName(), u1.getFirstName());
        assertEquals(um2.getLastName(), u1.getLastName());
        assertEquals(um2.getBirthdate(), u1.getDateOfBirth());
        assertEquals(um2.getBudget(), u1.getBudget());
    }

/*    public Optional<UserModifyOutput> findOneByEmailToModify(String email) {
        User user = userRepository.findFirstByUsername(email).orElseThrow(NoSuchElementException::new);
        UserModifyOutput userOut = new UserModifyOutput();
        userOut.setUsername(user.getUsername());
        userOut.setFirstName(user.getFirstName());
        userOut.setLastName(user.getLastName());
        userOut.setBirthdate(user.getDateOfBirth());
        userOut.setBudget(user.getBudget());
        return Optional.of(userOut);
    }*/

    @Test
    void saveTest() {
        when(userRepository.save(u1)).thenReturn(u1);
        assertThat(userRepository.save(u1), is(u1));
        verify(userRepository, times(1)).save(u1);
    }


    @Test
    void deleteByUsername() {
        userRepository.deleteByUsername("vajgi90@gmail.com");
        verify(userRepository, times(1)).deleteByUsername("vajgi90@gmail.com");
    }


}

