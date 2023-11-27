package com.example.credit.controller;

import com.example.credit.model.User;
import com.example.credit.repository.CreditRepository;
import com.example.credit.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditController.class)
class CreditControllerTest {

  @MockBean
  private CreditRepository creditRepository;

  @MockBean
  private UserService userService;

  @Autowired
  private CreditController creditController;

  @Test
  void findByUserIdTest() {
    User user = new User(1, "Alice", 750);
    when(creditRepository.findById(1)).thenReturn(Optional.of(user));

    ResponseEntity<User> response = creditController.findByUserId(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(user, response.getBody());
  }

  @Test
  void getCreditScoreTest() {
    User user = new User(1, "Bob", 680);
    when(creditRepository.findById(1)).thenReturn(Optional.of(user));

    ResponseEntity<Integer> response = creditController.getCreditScore(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(680, response.getBody());
  }

  @Test
  void addUserTest() {
    User newUser = new User(2, "Charlie", 710);
    when(userService.createUser(any(User.class))).thenReturn(newUser);

    ResponseEntity<User> response = creditController.addUser(newUser);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Charlie", response.getBody().getName());
    assertEquals(710, response.getBody().getCreditScore());
  }


}
