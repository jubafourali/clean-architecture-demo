package com.cleancode.cleanarchitecturedemo.usecase;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import com.cleancode.cleanarchitecturedemo.usecase.command.CreateUser;
import com.cleancode.cleanarchitecturedemo.usecase.input.UserInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class CreateUserTest {

    UserStorage userStorage = Mockito.mock(UserStorage.class);

    CreateUser createUser = new CreateUser(userStorage);

    @Nested
    public class GivenUserDoesNotExistWithGivenName {
        String name = "Ahmed";

        @Nested
        class WhenUserIsCreatedWithGivenName {
            UserInput userInput = new UserInput(name);

            @Test
            void thenShouldCreateUser() {
                User user = new User(name);
                Mockito.when(userStorage.exists(name)).thenReturn(Mono.just(false));
                Mockito.when(userStorage.save(any())).thenReturn(Mono.just(user));

                User response = createUser.invoke(userInput).block();
                Assertions.assertNotNull(response);
            }
        }
    }

    @Nested
    public class GivenUserExistWithGivenName {
        String name = "Ahmed";

        @Nested
        class WhenUserIsCreatedWithGivenName {
            UserInput userInput = new UserInput(name);

            @Test
            void thenShouldNotCreateUserAndReturnAnError() {
                Mockito.when(userStorage.exists(name)).thenReturn(Mono.just(true));

                Throwable error = assertThrows(Exception.class, () -> createUser.invoke(userInput).block());
                Assertions.assertEquals("User already exist", error.getMessage());
            }
        }
    }
}
