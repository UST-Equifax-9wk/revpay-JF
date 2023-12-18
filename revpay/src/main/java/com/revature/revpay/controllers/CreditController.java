package com.revature.revpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.revpay.entities.Credit;
import com.revature.revpay.services.CreditService;

@RestController
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    
    @GetMapping("/{credit_id}")
    public Credit getCreditById(@PathVariable Integer credit_id) {
        return creditService.findCreditById(credit_id);
    }
    
    @GetMapping("/{credit_num}")
    public Credit getCreditByCredit_num(@PathVariable String credit_num) {
        return creditService.findCreditByCredit_num(credit_num);
    }
    
    @GetMapping("/{credit_exp}")
    public Credit getCreditByCredit_exp(@PathVariable String credit_exp) {
        return creditService.findCreditByCredit_exp(credit_exp);
    }
    
    @GetMapping("/{credit_cvv}")
    public Credit getCreditByCredit_cvv(@PathVariable String credit_cvv) {
        return creditService.findCreditByCredit_cvv(credit_cvv);
    }
    
    @GetMapping("/{first_name}")
    public Credit getCreditByFirst_name(@PathVariable String first_name) {
        return creditService.findCreditByFirst_name(first_name);
    }
    
    @GetMapping("/{last_name}")
    public Credit getCreditByLast_name(@PathVariable String last_name) {
        return creditService.findCreditByLast_name(last_name);
    }
    
    @PostMapping
    public Credit addCredit(Credit credit) {
        return creditService.addCredit(credit);
    }
    
    @PutMapping
    public Credit updateCredit(Credit credit) {
        return creditService.updateCredit(credit);
    }
    
    @DeleteMapping
    public void deleteCredit(Credit credit) {
        creditService.deleteCredit(credit);
    }
}
