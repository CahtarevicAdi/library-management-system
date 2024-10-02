package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;


    @PutMapping("/loans/return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        loanService.returnBook(id);
        return ResponseEntity.ok("Book returned successfully.");
    }
}
