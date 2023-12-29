package com.revature.revpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.Loan;
import com.revature.revpay.services.LoanService;

/**
 * This class represents the controller for managing loans.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoanController {

    private final LoanService loanService;

    /**
     * Constructs a new LoanController with the specified LoanService.
     *
     * @param loanService the LoanService used for loan operations
     */
    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Retrieves all loans using an accoutn ID.
     *
     * @return a ResponseEntity containing a list of all loans and the HTTP status code
     */
    @GetMapping("/loans/{id}")
    public ResponseEntity<List<Loan>> getAllLoansByAccountId(@PathVariable("id") int id) {
        List<Loan> loans = loanService.findAllLoansByAccount_id(id);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    /**
     * Retrieves a loan by its ID.
     *
     * @param id the ID of the loan to retrieve
     * @return a ResponseEntity containing the Loan object and the HTTP status code
     */
    @GetMapping("/loan/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable("id") int id) {
        Loan loan = loanService.findLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new loan.
     *
     * @param loan the Loan object to create
     * @return a ResponseEntity containing the created Loan object and the HTTP status code
     */
    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        Loan createdLoan = loanService.addLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    /**
     * Updates a loan by its ID.
     *
     * @param id   the ID of the loan to update
     * @param loan the updated Loan object
     * @return a ResponseEntity containing the updated Loan object and the HTTP status code
     */
    @PutMapping("/loans")
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan) {
        Loan updatedLoan = loanService.updateLoan(loan);
        if (updatedLoan != null) {
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a loan by its ID.
     *
     * @param id the ID of the loan to delete
     * @return a ResponseEntity with the HTTP status code
     */
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Boolean> deleteLoan(@PathVariable("id") int id) {
        boolean deleted = loanService.deleteLoan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
