package com.noteswala.mynotes.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity // → tells Spring this is a database table
@Table(name = "users")
@Data // (Lombok) → auto-generates getters, setters, equals(), hashCode(), toString()
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // Annotations explained:
    // @Id → Primary key
    // @GeneratedValue → Auto-increment
    // @Column → Database column properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash; // Will store BCrypt hashed password

    @Column(nullable = false)
    private String name;

    // User role: BUYER or SELLER
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(columnDefinition = "TEXT")
    private String bio; // Optional bio/description

    // Timestamps
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Use this constructor for creating new users
    public User(String email, String passwordHash, String name, UserRole role) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}