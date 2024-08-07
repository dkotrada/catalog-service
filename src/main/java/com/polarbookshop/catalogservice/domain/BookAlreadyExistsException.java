package com.polarbookshop.catalogservice.domain;

// Prevent duplicate entries to be added to catalog
public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN " + isbn + " already exists.");
    }
}
