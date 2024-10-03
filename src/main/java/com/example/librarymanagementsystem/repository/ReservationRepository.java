package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByBookIdAndNotifiedFalse(Long bookId);
}
