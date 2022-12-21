package com.cleancode.cleanarchitecturedemo.usecase.command;

import com.cleancode.cleanarchitecturedemo.domain.Item;
import com.cleancode.cleanarchitecturedemo.usecase.input.ItemInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class CreateItemTest {

    ItemStorage itemStorage = Mockito.mock(ItemStorage.class);

    @Nested
    public class GivenItemDoesNotExist {
        String name = "book";

        @Nested
        class WhenItemIsCreatedWithEmptyName {
            ItemInput itemInput = new ItemInput("");

            @Test
            void thenShouldThrowError() {
                Mockito.when(itemStorage.exists(itemInput.getName())).thenReturn(Mono.just(true));

                CreateItem createItem = new CreateItem(itemStorage);
                Throwable error = assertThrows(Exception.class, () -> createItem.invoke(itemInput).block());
                Assertions.assertEquals("Item name cannot be empty", error.getMessage());
            }
        }

        @Nested
        class WhenItemIsCreatedWithName {
            ItemInput itemInput = new ItemInput(name);

            @Test
            void ShouldCreateItem() {
                Item item = new Item(name);
                CreateItem createItem = new CreateItem(itemStorage);
                Mockito.when(itemStorage.save(any())).thenReturn(Mono.just(item));
                Mockito.when(itemStorage.exists(any())).thenReturn(Mono.just(false));
                Item response = createItem.invoke(itemInput).block();

                Assertions.assertEquals(item.getName(), response.getName());
            }
        }

    }
}
