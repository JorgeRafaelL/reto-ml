package com.ejercicio.cupondecompra.service;

import com.ejercicio.cupondecompra.dao.ItemDao;
import com.ejercicio.cupondecompra.document.Item;
import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Service
@Slf4j
public class ItemService {

    private final ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Mono<Item> updateOrInsertItem(Item item) {
        return itemDao.finItemByIdItem(item.getItemId())
                .flatMap(itemDao::updateQuantityOfItem)
                .switchIfEmpty(itemDao.insertItem(item))
                .onErrorMap(throwable -> new MongoException(
                        EXCEPTION_MESSAGE_MONGODB_ERROR.concat(throwable.getMessage())));
    }

    public Flux<Item> getTopFavorites(Integer top) {
        return itemDao.getTopFavorites(top)
                .doOnComplete(() -> log.info(LOG_MESSAGE_GET_FAVORITES_ITEM))
                .onErrorMap(throwable -> new MongoException(
                        EXCEPTION_MESSAGE_GETTING_FAVORITES_ITEM_MONGODB_ERROR.concat(throwable.getMessage())));
    }
}
