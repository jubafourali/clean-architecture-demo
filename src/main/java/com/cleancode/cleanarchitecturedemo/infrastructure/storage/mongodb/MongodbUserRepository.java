package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb;

import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MongodbUserRepository extends ReactiveMongoRepository<UserDocument, UUID> {
}
