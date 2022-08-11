package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document.UserDocument;
import com.cleancode.cleanarchitecturedemo.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MongodbUserStorage implements UserStorage {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;
    @Autowired
    MongodbUserRepository mongodbUserRepository;

    @Override
    public Mono<User> save(User user) {
        UserDocument userDocument = new UserDocument(user.getId(), user.getName());
        return mongodbUserRepository.save(userDocument).map(UserDocument::toUser);
    }

    @Override
    public Mono<Boolean> exists(String name) {
        Criteria criteria = Criteria.where("name").is(name);
        return reactiveMongoTemplate.exists(Query.query(criteria), UserDocument.class);
    }

    @Override
    public Flux<User> getAll() {
        return mongodbUserRepository.findAll().map(UserDocument::toUser);
    }
}
