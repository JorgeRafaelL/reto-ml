package com.ejercicio.cupondecompra.dao;

import com.ejercicio.cupondecompra.document.Item;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Slf4j
@Repository
public class ItemDao {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ItemDao(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Mono<Item> finItemByIdItem(String itemId) {
        return reactiveMongoTemplate.findOne(
                        Query.query(Criteria.where(ITEM_ID_FIELD).is(itemId)), Item.class)
                .map(item -> {
                    log.info(LOG_MESSAGE_FINDING_ITEM_BY_ID_ITEM, new Gson().toJson(item));
                    return item;
                });
    }

    public Mono<Item> insertItem(Item item) {
        item.setQuantity(1);
        return reactiveMongoTemplate.insert(item)
                .map(item1 -> {
                    log.info(LOG_MESSAGE_INSERTING_ITEM, new Gson().toJson(item1));
                    return item1;
                });
    }

    public Mono<Item> updateQuantityOfItem(Item item) {
        final Integer quantityBefore = item.getQuantity();
        item.setQuantity(item.getQuantity() + 1);
        return reactiveMongoTemplate.save(item)
                .map(item1 -> {
                    log.info(LOG_MESSAGE_ITEM_QUANTITY_UPDATED
                            , item.getItemId()
                            , quantityBefore
                            , item1.getQuantity());
                    return item1;
                });
    }

    public Flux<Item> getTopFavorites(Integer top) {
        Query query = new Query();

        query.with(Sort.by(Sort.Direction.DESC, QUANTITY_FIELD));
        query.limit(top);
        query.fields()
                .exclude(MONGO_ID_FIELD)
                .exclude(TITLE_FIELD)
                .exclude(PRICE_FIELD)
                .exclude(SITE_ID_FIELD);

        return reactiveMongoTemplate.find(query, Item.class);
    }
}
