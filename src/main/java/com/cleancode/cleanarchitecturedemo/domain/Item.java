package com.cleancode.cleanarchitecturedemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable {
    private UUID id = UUID.randomUUID();
    private String name;

    public Item(String name) {
        this.name = name;
    }
}
