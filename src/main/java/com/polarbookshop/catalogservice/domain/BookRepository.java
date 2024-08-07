package com.polarbookshop.catalogservice.domain;

import java.util.Optional;

// This interface belongs to a domain layer but it's implementation is in persistence layer.
public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
