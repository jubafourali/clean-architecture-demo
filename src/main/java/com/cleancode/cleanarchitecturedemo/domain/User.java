package com.cleancode.cleanarchitecturedemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class User {
    private UUID id = UUID.randomUUID();
    private String name;

    public User(String name) {
        this.name = name;
    }
}
