package com.noteswala.mynotes.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Markdown content of the note
    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentMarkdown;

    // Price of the note (null if free)
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    // Is this note free?
    @Column(nullable = false)
    private Boolean isFree = false;

    // Is this note published/visible to public?
    @Column(nullable = false)
    private Boolean published = false;

    // Foreign key: seller (User who created this note)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Constructor for creating new notes
    public Note(String title, String description, String contentMarkdown,
            BigDecimal price, Boolean isFree, User seller) {
        this.title = title;
        this.description = description;
        this.contentMarkdown = contentMarkdown;
        this.price = price;
        this.isFree = isFree;
        this.seller = seller;
        this.published = false; // New notes start unpublished
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}