package com.cleancode.cleanarchitecturedemo.usecase.command;


import com.cleancode.cleanarchitecturedemo.domain.Item;
import reactor.core.publisher.Mono;

public interface ItemStorage {

    Mono<Item> save(Item item);

    Mono<Boolean> exists(String name);

}
