package com.bank.app.bankNTI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;
    private String address;
    private String phoneNumber;
    private String email;

    //branch
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    @JsonBackReference
    private Branch branch;
    //accounts

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Account> accounts;
}
