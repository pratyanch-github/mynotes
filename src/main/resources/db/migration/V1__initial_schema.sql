-- When we run the app 
-- ✅ Flyway will run automatically
-- ✅ CREATE tables: users, notes, purchases
-- ✅ CREATE indexes
-- ✅ App starts on http://localhost:8080



-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    bio TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Notes table
CREATE TABLE notes (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    content_markdown TEXT NOT NULL,
    price NUMERIC(10, 2),
    is_free BOOLEAN NOT NULL DEFAULT false,
    published BOOLEAN NOT NULL DEFAULT false,
    seller_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Purchases table
CREATE TABLE purchases (
    id BIGSERIAL PRIMARY KEY,
    buyer_id BIGINT NOT NULL,
    note_id BIGINT NOT NULL,
    purchased_at TIMESTAMP NOT NULL,
    FOREIGN KEY (buyer_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (note_id) REFERENCES notes(id) ON DELETE CASCADE,
    UNIQUE(buyer_id, note_id)
);

-- Create indexes for faster queries
CREATE INDEX idx_notes_seller_id ON notes(seller_id);
CREATE INDEX idx_notes_published ON notes(published);
CREATE INDEX idx_purchases_buyer_id ON purchases(buyer_id);
CREATE INDEX idx_purchases_note_id ON purchases(note_id);