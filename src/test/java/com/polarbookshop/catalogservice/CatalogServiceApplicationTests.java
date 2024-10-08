package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("IntegrationTests")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

    private WebTestClient webTestClient;

    @Autowired
    public CatalogServiceApplicationTests(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Test
    @DisplayName("When POST request then Book created.")
    void whenPostRequestThenBookCreated() {
        var expectBook = Book.of("1231231231", "Title", "Author", 9.90, "Manning");

        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.isbn()).isEqualTo(expectBook.isbn());
                });
    }
}
