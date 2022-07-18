package com.cleancode.cleanarchitecturedemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
@DataMongoTest
class CleanArchitectureDemoApplicationTests {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(reactiveMongoTemplate);
    }

}
