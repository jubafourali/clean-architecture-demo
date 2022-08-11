package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document.UserDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class MongodbUserStorageTest {

    @Autowired
    MongodbUserStorage mongodbUserStorage;

    @Test
    void shouldSaveUserDocument() {
        UserDocument userDocument = new UserDocument(UUID.randomUUID(), "Juba");
        User user = mongodbUserStorage.save(userDocument).block();

        Assertions.assertEquals(userDocument.getId(), user.getId());
        Assertions.assertEquals(userDocument.getName(), user.getName());
    }

    @Test
    void shouldReturnTrueWhenUserExistsWithGivenName() {
        UserDocument userDocument = new UserDocument(UUID.randomUUID(), "Juba");
        mongodbUserStorage.save(userDocument).block();

        Boolean response = mongodbUserStorage.exists(userDocument.getName()).block();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response);
    }

    @Test
    void shouldReturnFalseWhenUserDoesNotExistWithGivenName() {
        Boolean response = mongodbUserStorage.exists("random").block();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response);
    }

    @Test
    void shouldReturnAllUsersWhenAUserExists() {
        mongodbUserStorage.save(new User("Juba")).block();

        List<User> response = mongodbUserStorage.getAll().collectList().block();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoUserExist() {
        List<User> response = mongodbUserStorage.getAll().collectList().block();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(0, response.size());
    }
}
