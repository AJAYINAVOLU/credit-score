package com.example.credit.controller;

import com.example.credit.model.LoanApplication;
import com.example.credit.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class LoanControllerTest {

  @Mock
  private LoanRepository loanRepository;

  @InjectMocks
  private LoanController loanController;

  @Test
  void getLoanApplicationTest() {
    LoanApplication loan = new LoanApplication();
    loan.setLoanId(101);
    when(loanRepository.findById(101)).thenReturn(Optional.of(loan));

    ResponseEntity<LoanApplication> response = loanController.getLoanApplication(101);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(loan, response.getBody());
  }

  @Test
  void createLoanApplicationTest() {
    LoanApplication newLoan = new LoanApplication();
    newLoan.setUserId(1);
    newLoan.setLoanAmount(20000.00);
    newLoan.setPurpose("Business");
    newLoan.setApproval(false);

    when(loanRepository.save(any(LoanApplication.class))).thenReturn(newLoan);

    ResponseEntity<LoanApplication> response = loanController.createLoanApplication(newLoan);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(20000.00, response.getBody().getLoanAmount());
    assertEquals("Business", response.getBody().getPurpose());
    assertFalse(response.getBody().isApproval());
  }

  @Test
  void updateLoanApplicationTest() {
    LoanApplication existingLoan = new LoanApplication();
    existingLoan.setLoanId(101);
    existingLoan.setUserId(1);
    existingLoan.setLoanAmount(15000.00);
    existingLoan.setPurpose("Education");
    existingLoan.setApproval(true);

    LoanApplication updatedInfo = new LoanApplication();
    updatedInfo.setUserId(1);
    updatedInfo.setLoanAmount(20000.00);
    updatedInfo.setPurpose("Business");
    updatedInfo.setApproval(false);

    when(loanRepository.findById(101)).thenReturn(Optional.of(existingLoan));
    when(loanRepository.save(any(LoanApplication.class))).thenReturn(updatedInfo);

    ResponseEntity<LoanApplication> response = loanController.updateLoanApplication(101, updatedInfo);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(20000.00, response.getBody().getLoanAmount());
    assertEquals("Business", response.getBody().getPurpose());
    assertFalse(response.getBody().isApproval());
  }


}
