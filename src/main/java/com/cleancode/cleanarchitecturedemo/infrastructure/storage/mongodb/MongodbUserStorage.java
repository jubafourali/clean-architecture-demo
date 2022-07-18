package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document.UserDocument;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

public class MongodbUserStorage implements UserStorage {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<User> save(User user) {
        UserDocument userDocument = new UserDocument(user.getId(), user.getName());
        return reactiveMongoTemplate.save(userDocument);
    }

    @Override
    public Mono<Boolean> exists(String name) {
        return null;
    }

}
