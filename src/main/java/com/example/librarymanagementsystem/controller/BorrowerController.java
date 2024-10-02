package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Borrower;
import com.example.librarymanagementsystem.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }


    @GetMapping
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerService.getAllBorrowers();
        return new ResponseEntity<>(borrowers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long id) {
        Optional<Borrower> borrower = borrowerService.getBorrowerById(id);
        return borrower.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) {
        Borrower newBorrower = borrowerService.saveBorrower(borrower);
        return new ResponseEntity<>(newBorrower, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Borrower> updateBorrower(@PathVariable Long id, @RequestBody Borrower updatedBorrower) {
        Optional<Borrower> borrowerData = borrowerService.getBorrowerById(id);

        if (borrowerData.isPresent()) {
            Borrower borrower = borrowerData.get();
            borrower.setName(updatedBorrower.getName());
            borrower.setEmail(updatedBorrower.getEmail());
            borrower.setPhone(updatedBorrower.getPhone());

            Borrower savedBorrower = borrowerService.saveBorrower(borrower);
            return new ResponseEntity<>(savedBorrower, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
        boolean isDeleted = borrowerService.deleteBorrower(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
