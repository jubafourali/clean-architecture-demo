package com.cleancode.cleanarchitecturedemo.infrastructure.controller;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.MongodbUserStorage;
import com.cleancode.cleanarchitecturedemo.usecase.input.UserInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.Nested;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@DirtiesContext
class UserControllerTest {

    @Autowired
    MongodbUserStorage mongodbUserStorage;

    @Autowired
    WebTestClient webTestClient;

    @Nested
    class CreateUserEndpoint {

        @Test
        void shouldCreateUser() {
            UserInput input = new UserInput("Juba");
            User response = webTestClient.post()
                    .uri("/api/v1/users")
                    .bodyValue(input)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus()
                    .isCreated()
                    .expectBody(User.class)
                    .returnResult()
                    .getResponseBody();

            Assertions.assertNotNull(response);
            Assertions.assertEquals(input.getName(), response.getName());
        }
    }

    @Nested
    class GetAllUserEndpointTest {

        @Test
        void shouldReturnListOfUsers() {
            mongodbUserStorage.save(new User("Juba")).block();
            List<User> response = webTestClient.get()
                    .uri("/api/v1/users")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBodyList(User.class)
                    .returnResult()
                    .getResponseBody();

            Assertions.assertNotNull(response);
            Assertions.assertEquals(1, response.size());
        }

        @Test
        void shouldReturnEmptyListOfUsers() {
            List<User> response = webTestClient.get()
                    .uri("/api/v1/users")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBodyList(User.class)
                    .returnResult()
                    .getResponseBody();

            Assertions.assertNotNull(response);
            Assertions.assertEquals(0, response.size());
        }
    }
}
