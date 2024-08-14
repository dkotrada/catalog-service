package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookrepository) {
        this.bookRepository = bookrepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    // When editing the book, all the Book fields can be updated except the ISBN code,
                    // because it’s the entity identifier.
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            existingBook.version()
                    );
                    return bookRepository.save(bookToUpdate);
                })
                // When changing the details for a book not in the catalog yet, create a new book.
                .orElseGet(() -> addBookToCatalog(book));
    }
}
