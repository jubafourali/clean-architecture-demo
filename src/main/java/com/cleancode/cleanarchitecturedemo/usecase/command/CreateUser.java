package com.cleancode.cleanarchitecturedemo.usecase.command;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import com.cleancode.cleanarchitecturedemo.usecase.input.UserInput;
import reactor.core.publisher.Mono;

public class CreateUser {

    private final UserStorage userStorage;

    public CreateUser(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Mono<User> invoke(UserInput input) {
        return validate(input).flatMap(it -> {
            User user = new User(input.getName());
            return userStorage.save(user);
        });
    }

    private Mono<Boolean> validate(UserInput input) {
        return userStorage.exists(input.getName()).flatMap(exists -> {
            if (Boolean.TRUE.equals(exists)) return Mono.error(new IllegalArgumentException("User already exist"));
            return Mono.just(true);
        });
    }
}
