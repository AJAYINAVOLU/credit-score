package com.example.credit.controller;

import com.example.credit.model.LoanApplication;
import com.example.credit.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

  @Autowired
  private LoanRepository loanApplicationRepository;

  @GetMapping("/{loanId}")
  public ResponseEntity<LoanApplication> getLoanApplication(@PathVariable int loanId) {
    return loanApplicationRepository.findById(loanId)
            .map(loan -> new ResponseEntity<>(loan, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplication loanApplication) {
    LoanApplication savedLoan = loanApplicationRepository.save(loanApplication);
    return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
  }

  @PutMapping("/{loanId}")
  public ResponseEntity<LoanApplication> updateLoanApplication(@PathVariable int loanId, @RequestBody LoanApplication loanApplication) {
    return loanApplicationRepository.findById(loanId)
            .map(existingLoan -> {
              existingLoan.setUserId(loanApplication.getUserId());
              existingLoan.setLoanAmount(loanApplication.getLoanAmount());
              existingLoan.setPurpose(loanApplication.getPurpose());
              existingLoan.setApproval(loanApplication.isApproval());
              LoanApplication updatedLoan = loanApplicationRepository.save(existingLoan);
              return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
