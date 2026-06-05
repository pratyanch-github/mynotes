package com.noteswala.mynotes.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
// @AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;

    @Column(nullable = false, updatable = false)
    private LocalDateTime purchasedAt;

    // Custom constructor with automatic purchasedAt timestamp
    public Purchase(Note note, User buyer) {
        this.note = note;
        this.buyer = buyer;
        this.purchasedAt = LocalDateTime.now();
        // no new keyword needed;
        // they used a design pattern called a Static Factory Method.
        // now() is a static method belonging to the LocalDateTime class that handles
        // the object creation for you and returns the instance.
    }

}

// package com.noteswala.mynotes.Models;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "purchases")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Purchase {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;

// // Buyer of the note
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "buyer_id", nullable = false)
// private User buyer;

// // The note that was purchased
// @ManyToOne(fetch = FetchType.LAZY)
// @JoinColumn(name = "note_id", nullable = false)
// private Note note;

// @Column(nullable = false, updatable = false)
// private LocalDateTime purchasedAt;

// // Constructor
// public Purchase(User buyer, Note note) {
// this.buyer = buyer;
// this.note = note;
// this.purchasedAt = LocalDateTime.now();
// }
// }