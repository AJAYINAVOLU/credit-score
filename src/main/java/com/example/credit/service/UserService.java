package com.example.credit.service;

import com.example.credit.repository.CreditRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.credit.model.User;


@Service
public class UserService {

  private final CreditRepository creditRepository;

  public UserService(CreditRepository creditRepository) {
    this.creditRepository = creditRepository;
  }

  public int getCreditScore(int userId) throws Exception {
    return creditRepository.findById(userId)
            .map(User::getCreditScore)
            .orElseThrow(() -> new Exception("User not found with id: " + userId));
  }

  public User createUser(User userDto) {
    User newUser = new User(userDto.getUserId(), userDto.getName(), userDto.getCreditScore());
    return creditRepository.save(newUser);
  }


}
