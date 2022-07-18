package com.cleancode.cleanarchitecturedemo.storage;

import com.cleancode.cleanarchitecturedemo.domain.User;
import reactor.core.publisher.Mono;

public interface UserStorage {
    Mono<User> save(User user);
    Mono<Boolean> exists(String name);
}
