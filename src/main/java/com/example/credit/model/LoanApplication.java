package com.example.credit.model;

import jakarta.persistence.*;


@Entity
@Table(name = "loan_applications")
public class LoanApplication {

  @Id
  private int loanId;
  private int userId;
  private double loanAmount;
  private String purpose;
  private boolean approval;

  public int getLoanId() {
    return loanId;
  }

  public void setLoanId(int loanId) {
    this.loanId = loanId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public double getLoanAmount() {
    return loanAmount;
  }

  public void setLoanAmount(double loanAmount) {
    this.loanAmount = loanAmount;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public boolean isApproval() {
    return approval;
  }

  public void setApproval(boolean approval) {
    this.approval = approval;
  }
}
