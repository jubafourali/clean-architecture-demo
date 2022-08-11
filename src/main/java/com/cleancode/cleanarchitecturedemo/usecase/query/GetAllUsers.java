package com.cleancode.cleanarchitecturedemo.usecase.query;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import reactor.core.publisher.Flux;

public class GetAllUsers {

    final UserStorage userStorage;

    public GetAllUsers(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Flux<User> invoke() {
        return userStorage.getAll();
    }
}
