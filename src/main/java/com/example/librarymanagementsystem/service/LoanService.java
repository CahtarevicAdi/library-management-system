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

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    public void returnBook(Loan loan, LocalDate returnDate) {
        loan.setReturnDate(returnDate);
        loanRepository.save(loan);


        List<Reservation> reservations = reservationRepository.findByBookIdAndNotifiedFalse(loan.getBook().getId());
        if (!reservations.isEmpty()) {
            Reservation nextReservation = reservations.get(0);
            nextReservation.setNotified(true);
            reservationRepository.save(nextReservation);

        }
    }
}
