package com.cleancode.cleanarchitecturedemo.usecase.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInput {
    String name;

    public UserInput(String name) {
        this.name = name;
    }
}
