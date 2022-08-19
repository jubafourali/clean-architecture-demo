package com.cleancode.cleanarchitecturedemo.usecase.command;

import com.cleancode.cleanarchitecturedemo.domain.Item;
import com.cleancode.cleanarchitecturedemo.usecase.input.ItemInput;
import reactor.core.publisher.Mono;

public class CreateItem {

    public Mono<Item> invoke(ItemInput itemInput) {
        return Mono.error(new IllegalArgumentException("Item name cannot be empty"));
    }
}
