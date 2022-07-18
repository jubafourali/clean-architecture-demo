package com.cleancode.cleanarchitecturedemo.usecase.input;

import lombok.Data;

@Data
public class UserInput {
    String name;

    public UserInput(String name) {
        this.name = name;
    }
}
