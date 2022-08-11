package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document;

import com.cleancode.cleanarchitecturedemo.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = Collections.USERS)
public class UserDocument extends User {

    private @Id UUID id;

    public UserDocument(UUID id, String name) {
        super(id, name);
        this.id = id;
    }

    public User toUser() {
        return new User(
            this.id,
            this.getName()
        );
    }

}
