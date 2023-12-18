package com.revature.revpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.revpay.entities.Credit;
import com.revature.revpay.repositories.CreditRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public Credit findCreditById(Integer credit_id) {
        return creditRepository.findByCredit_id(credit_id).orElseThrow();
    }

    public Credit findCreditByCredit_num(String credit_num) {
        return creditRepository.findByCredit_num(credit_num).orElseThrow();
    }

    public Credit findCreditByCredit_exp(String credit_exp) {
        return creditRepository.findByCredit_exp(credit_exp).orElseThrow();
    }

    public Credit findCreditByCredit_cvv(String credit_cvv) {
        return creditRepository.findByCredit_cvv(credit_cvv).orElseThrow();
    }

    public Credit findCreditByFirst_name(String first_name) {
        return creditRepository.findByFirst_name(first_name).orElseThrow();
    }

    public Credit findCreditByLast_name(String last_name) {
        return creditRepository.findByLast_name(last_name).orElseThrow();
    }

    public Credit addCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public Credit updateCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public void deleteCredit(Credit credit) {
        creditRepository.delete(credit);
    }

    
}
