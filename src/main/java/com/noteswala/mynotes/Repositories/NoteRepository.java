package com.noteswala.mynotes.Repositories;

import com.noteswala.mynotes.Models.Note;
import com.noteswala.mynotes.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Find all published notes (for public listing)
    // Pageable = pagination support (page number, size, sorting)
    // Page = Spring object that has totalElements, totalPages, etc.
    Page<Note> findByPublishedTrue(Pageable pageable);

    // Find all notes by a specific seller (public notes only)
    Page<Note> findBySellerAndPublishedTrue(User seller, Pageable pageable);

    // Search notes by title or description (custom @Query needed)
    // LOWER = case-insensitive search
    // CONCAT = string concatenation
    // % = wildcard (% means any characters)
    @Query("SELECT n FROM Note n WHERE n.published = true AND " +
            "(LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(n.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Note> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}