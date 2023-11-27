package com.example.credit.repository;

import com.example.credit.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserId(int userId);
//  @Query("SELECT u.creditScore FROM User u WHERE u.userId = :userId")
//  int getCreditScore(@Param("userId") int userId);

}
