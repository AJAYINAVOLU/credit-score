package com.example.credit.model;
import jakarta.persistence.*;
@Entity
@Table(name = "credit_info")
public class User {
  @Id
  @Column(name = "user_id")
  private int userId;
  @Column(name = "name")
  private String name;
  @Column(name = "credit_score")
  private int creditScore;


  public User(){}

  public User(int userId, String name, int credit_score){
    this.userId = userId;
    this.name = name;
    this.creditScore = credit_score;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int user_id) {
    this.userId = user_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCreditScore() {
    return creditScore;
  }

  public void setCreditScore(int credit_score) {
    this.creditScore = credit_score;
  }

  public String toString(){
    return "User " + userId + " with name " + name + " has credit score " + creditScore;
  }
}
