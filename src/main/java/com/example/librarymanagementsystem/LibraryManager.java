package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Borrower;
import com.example.librarymanagementsystem.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {


    private static LibraryManager instance = null;


    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Loan> loans;


    private LibraryManager() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
        loans = new ArrayList<>();
    }


    public static LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public void removeBook(Book book) {
        books.remove(book);
    }


    public List<Book> getAllBooks() {
        return books;
    }


    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }


    public void removeBorrower(Borrower borrower) {
        borrowers.remove(borrower);
    }


    public List<Borrower> getAllBorrowers() {
        return borrowers;
    }


    public void addLoan(Loan loan) {
        loans.add(loan);
    }


    public void returnBook(Loan loan) {
        loans.remove(loan);
    }


    public List<Loan> getAllLoans() {
        return loans;
    }


}
