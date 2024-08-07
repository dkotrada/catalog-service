package com.polarbookshop.catalogservice.domain;

// We need to know, is the book in catalog or not.
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("The book with ISBN Number " + isbn + " could not be found.");
    }
}
