package com.cleancode.cleanarchitecturedemo.usecase.command;

import com.cleancode.cleanarchitecturedemo.domain.Item;
import com.cleancode.cleanarchitecturedemo.usecase.input.ItemInput;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CreateItem {
    ItemStorage itemStorage;

    public Mono<Item> invoke(ItemInput itemInput) {
        return itemStorage.exists(itemInput.getName()).flatMap(exists -> {
            if (Boolean.TRUE.equals(exists)) {
                return Mono.error(new IllegalArgumentException("Item name cannot be empty"));
            }
            Item item = new Item(itemInput.getName());
            return itemStorage.save(item);
        });
    }
}
