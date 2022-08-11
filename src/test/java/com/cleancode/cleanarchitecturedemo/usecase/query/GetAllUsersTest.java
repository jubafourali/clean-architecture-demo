package com.cleancode.cleanarchitecturedemo.usecase.query;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.mockito.Mockito.mock;

public class GetAllUsersTest {

    UserStorage userStorage = mock(UserStorage.class);

    GetAllUsers getAllUser = new GetAllUsers(userStorage);

    @Nested
    class GivenUsersExists {

        Flux<User> users = Flux.just(
                new User("Juba"),
                new User("Ahmed")
        );

        @Nested
        class WhenFetchAllUsers {

            @Test
            void shouldReturnListOfUsers() {
                Mockito.when(userStorage.getAll()).thenReturn(users);
                List<User> response = getAllUser.invoke().collectList().block();

                Assertions.assertNotNull(response);
                Assertions.assertEquals(2, response.size());
            }
        }

    }

    @Nested
    class GivenNoUsersExist {

        @Nested
        class WhenFetchAllUser {

            @Test
            void shouldReturnEmptyList() {
                Mockito.when(userStorage.getAll()).thenReturn(Flux.empty());
                List<User> response = getAllUser.invoke().collectList().block();

                Assertions.assertNotNull(response);
                Assertions.assertEquals(0, response.size());
            }
        }

    }
}
