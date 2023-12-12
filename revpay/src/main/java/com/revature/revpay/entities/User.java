package com.revature.revpay.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;  // added

import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;

public class User {
    UUID id;
    String username;
    String email;
    String phoneNumber;
    String creditCardNumber;
}
