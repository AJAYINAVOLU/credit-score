package com.example.credit.repository;

import com.example.credit.model.LoanApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanApplication, Integer> {

}
