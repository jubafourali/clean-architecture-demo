package com.cleancode.cleanarchitecturedemo.usecase.command;

import com.cleancode.cleanarchitecturedemo.usecase.input.ItemInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateItemTest {
    @Nested
    public class GivenItemDoesNotExist{
        String name = "book";

        @Nested
        class WhenItemIsCreatedWithEmptyName{
            ItemInput itemInput = new ItemInput("");
            @Test
            void thenShouldThrowError(){
                CreateItem createItem = new CreateItem();
                Throwable error = assertThrows(Exception.class, () -> createItem.invoke(itemInput).block());
                Assertions.assertEquals("Item name cannot be empty", error.getMessage());
            }
        }

    }
}
