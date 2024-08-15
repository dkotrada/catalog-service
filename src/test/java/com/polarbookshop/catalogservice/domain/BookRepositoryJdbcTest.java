package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)

@ActiveProfiles("integration")
public class BookRepositoryJdbcTest {

    private BookRepository bookRepository;

    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Autowired
    public BookRepositoryJdbcTest(BookRepository bookRepository, JdbcAggregateTemplate jdbcAggregateTemplate) {
        this.bookRepository = bookRepository;
        this.jdbcAggregateTemplate = jdbcAggregateTemplate;
    }


    @Test
    void findBookByIsbnWhenExisting() {
        String bookIsbn = "1234561237";
        Book book = Book.of(bookIsbn, "Title", "Author", 12.9);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
