package com.revature.revpay.entities;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "user_id", columnList = "id", unique = true)})
public class Credit {
    @ManyToOne
    @Column(name = "user_id", nullable = false)
    @JsonBackReference
    private User user_id;

    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer credit_id;

    @Column(name = "first_name", nullable = false, length = 60)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 60)
    private String last_name;

    @Column(name = "credit_num", nullable = false, length = 16)
    private String credit_num;

    @Column(name = "credit_exp", nullable = false, length = 4)
    private String credit_exp;

    @Column(name = "credit_cvv", nullable = false, length = 3)
    private String credit_cvv;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Credit> users;


    public Credit(User user_id, Integer credit_id, String credit_num, String credit_exp, String credit_cvv, String first_name, String last_name) {
        this.user_id = user_id;
        this.credit_id = credit_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.credit_num = credit_num;
        this.credit_exp = credit_exp;
        this.credit_cvv = credit_cvv;
    }


}
