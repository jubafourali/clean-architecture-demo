package com.cleancode.cleanarchitecturedemo.infrastructure.storage.mongodb.document;

import com.cleancode.cleanarchitecturedemo.domain.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = Collections.ITEMS)
public class ItemDocument extends Item {

    private @Id UUID id;

    public ItemDocument(UUID id, String name) {
        super(id, name);
        this.id = id;
    }

    public Item toItem() {
        return new Item(
                this.id,
                this.getName()
        );
    }
}
