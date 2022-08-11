package com.cleancode.cleanarchitecturedemo.infrastructure.controller;

import com.cleancode.cleanarchitecturedemo.domain.User;
import com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.MongodbUserStorage;
import com.cleancode.cleanarchitecturedemo.usecase.command.CreateUser;
import com.cleancode.cleanarchitecturedemo.usecase.input.UserInput;
import com.cleancode.cleanarchitecturedemo.usecase.query.GetAllUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    final MongodbUserStorage userStorage;
    final CreateUser createUserCommand;
    final GetAllUsers getAllUsers;

    public UserController(MongodbUserStorage userStorage) {
        this.userStorage = userStorage;
        this.createUserCommand = new CreateUser(userStorage);
        this.getAllUsers = new GetAllUsers(userStorage);
    }

    @PostMapping
    Mono<ResponseEntity<User>> createUser(@RequestBody UserInput input) {
        Mono<User> response = createUserCommand.invoke(input);
        return response.map(it ->
                new ResponseEntity<>(it, HttpStatus.CREATED)
        );
    }

    @GetMapping
    Mono<ResponseEntity<List<User>>> getUsers() {
        return getAllUsers.invoke().collectList().map(it ->
                new ResponseEntity<>(it, HttpStatus.OK)
        );
    }
}
