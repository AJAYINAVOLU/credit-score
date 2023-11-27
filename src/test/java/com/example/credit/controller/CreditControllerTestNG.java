package com.example.credit.controller;

import com.example.credit.model.User;
import com.example.credit.repository.CreditRepository;
import com.example.credit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CreditControllerTestNG {

  @MockBean
  private CreditRepository creditRepository;

  @MockBean
  private UserService userService;

  @Autowired
  private CreditController creditController;

  @BeforeMethod
  public void setUp() {
    // Mock setup or any initialization before each test method
  }

  @Test
  public void findByUserIdTest() {
    User user = new User(1, "Alice", 750);
    when(creditRepository.findById(1)).thenReturn(Optional.of(user));

    ResponseEntity<User> response = creditController.findByUserId(1);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assert.assertEquals(response.getBody(), user);
  }

  @Test
  public void getCreditScoreTest() {
    User user = new User(1, "Bob", 680);
    when(creditRepository.findById(1)).thenReturn(Optional.of(user));

    ResponseEntity<Integer> response = creditController.getCreditScore(1);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assert.assertEquals(response.getBody().intValue(), 680);
  }

  @Test
  public void addUserTest() {
    User newUser = new User(2, "Charlie", 710);
    when(userService.createUser(any(User.class))).thenReturn(newUser);

    ResponseEntity<User> response = creditController.addUser(newUser);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals(response.getBody().getName(), "Charlie");
    Assert.assertEquals(response.getBody().getCreditScore(), 710);
  }
}
