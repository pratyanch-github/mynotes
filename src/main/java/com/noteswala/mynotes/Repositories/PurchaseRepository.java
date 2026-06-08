package com.noteswala.mynotes.Repositories;

import com.noteswala.mynotes.Models.Purchase;
import com.noteswala.mynotes.Models.User;
import com.noteswala.mynotes.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    // Check if user already owns this note
    // Returns Optional because might not have purchase
    Optional<Purchase> findByBuyerAndNote(User buyer, Note note);

    // Check if user owns a note (returns boolean directly)
    boolean existsByBuyerAndNote(User buyer, Note note);
}