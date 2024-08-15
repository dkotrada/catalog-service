package com.polarbookshop.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// This interface belongs to a domain layer but it's implementation is in persistence layer.
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    @Modifying
    @Transactional
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
