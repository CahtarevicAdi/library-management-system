package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Borrower;
import com.example.librarymanagementsystem.model.Loan;
import com.example.librarymanagementsystem.model.Reservation;
import com.example.librarymanagementsystem.repository.LoanRepository;
import com.example.librarymanagementsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    public void returnBook(Long loanId) {

        Optional<Loan> loanOptional = loanRepository.findById(loanId);

        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();

            LocalDate returnDate = LocalDate.now();
            loan.setReturnDate(returnDate);
            loanRepository.save(loan);


            List<Reservation> reservations = reservationRepository.findByBookIdAndNotifiedFalse(loan.getBook().getId());
            if (!reservations.isEmpty()) {
                Reservation nextReservation = reservations.get(0);
                nextReservation.setNotified(true);
                reservationRepository.save(nextReservation);


            }
        } else {
            throw new IllegalArgumentException("Posudba sa ID-om " + loanId + " nije pronađena.");
        }
    }


    public void createLoan(Loan loan) {
        loanRepository.save(loan);
    }


    public boolean extendLoan(Loan loan) {

        if (loan.getBorrower().getBorrowerType() == Borrower.BorrowerType.STANDARD) {
            if (loan.getExtensions() < 1) {
                loan.setDueDate(loan.getDueDate().plusDays(7));
                loan.setExtensions(loan.getExtensions() + 1);
                loanRepository.save(loan);
                return true;
            }
        } else if (loan.getBorrower().getBorrowerType() == Borrower.BorrowerType.PREMIUM) {
            if (loan.getExtensions() < 2) {
                loan.setDueDate(loan.getDueDate().plusDays(10));
                loan.setExtensions(loan.getExtensions() + 1);
                loanRepository.save(loan);
                return true;
            }
        }
        return false;
    }
}
