package com.example.librarymanagementsystem.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private List<Loan> borrowedBooks;

    public enum BorrowerType {
        STANDARD, PREMIUM
    }

    private BorrowerType borrowerType;


    private String role;


    public Borrower() {
    }


    public Borrower(String name, String email, String phone, BorrowerType borrowerType, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowerType = borrowerType;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Loan> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Loan> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public BorrowerType getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(BorrowerType borrowerType) {
        this.borrowerType = borrowerType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public double calculateFine(int overdueDays) {
        if (borrowerType == BorrowerType.STANDARD) {
            return overdueDays * 1.0;
        } else if (borrowerType == BorrowerType.PREMIUM) {
            return overdueDays * 0.5;
        }
        return 0;
    }
}
