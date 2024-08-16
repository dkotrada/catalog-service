package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    private JacksonTester<Book> json;

    @Autowired
    public BookJsonTests(JacksonTester<Book> json) {
        this.json = json;
    }

    @Test
    void testSerialize() throws IOException {
        var book = Book.of("1234567890","Title","Author", 9.90, "Manning");
        JsonContent<Book> content = json.write(book);

        assertThat(content)
                .extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(content)
                .extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(content)
                .extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(content)
                .extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
      {
        "isbn": "1234567890",
        "title": "Title",
        "author": "Author",
        "price": 9.90,
        "publisher": "Manning"
      }
      """;
        ObjectContent<Book> book = json.parse(content);
        assertThat(book.getObject())
      .usingRecursiveComparison()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, "Manning"));
    }
}
