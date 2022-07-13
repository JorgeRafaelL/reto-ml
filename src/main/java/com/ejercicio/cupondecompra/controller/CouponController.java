package com.ejercicio.cupondecompra.controller;

import com.ejercicio.cupondecompra.document.Item;
import com.ejercicio.cupondecompra.exception.DuplicateItemIdsExcpetion;
import com.ejercicio.cupondecompra.exception.NotItemsFoundForAmountGivenException;
import com.ejercicio.cupondecompra.model.Request;
import com.ejercicio.cupondecompra.service.CouponService;
import com.ejercicio.cupondecompra.service.ItemService;
import com.ejercicio.cupondecompra.util.Util;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Slf4j
@RestController
@RequestMapping(PATH_REQUEST_MAPPING)
public class CouponController {

    private final CouponService couponService;
    private final ItemService itemService;
    @Value(NUMBER_TOP_FAVORITES)
    private String topFavorites;

    public CouponController(CouponService couponService, ItemService itemService) {
        this.couponService = couponService;
        this.itemService = itemService;
    }


    @PostMapping()
    public Mono<ResponseEntity<?>> spendMaxPossible(@RequestBody @Valid Request request) throws Exception {
        Gson gson = new Gson();

        List<String> duplicates = Util.findDuplicates(request.getItemIds());
        if (!duplicates.isEmpty())
            throw new DuplicateItemIdsExcpetion(EXCEPTION_MESSAGE_DUPLICATED_ITEMS,
                    new Throwable(EXCEPTION_MESSAGE_DUPLICATED_ITEMS_DETAIL.concat(String.valueOf(duplicates))));

        log.info(LOG_MESSAGE_START_GET_THE_MAXIMUM_SPEND_POSIBLE, gson.toJson(request));

        return couponService.getMaxSpendPossible(request)
                .map(response -> {
                    if (response.isEmpty())
                        throw new NotItemsFoundForAmountGivenException(EXCEPTION_MESSAGE_NO_ITEMS_FOUND);

                    log.info(LOG_MESSAGE_END_GET_THE_MAXIMUM_SPEND_POSIBLE
                            , gson.toJson(response.get()));
                    return ResponseEntity.status(HttpStatus.CREATED).body(response.get());
                });

    }

    @GetMapping(PATH_GET_MAPPING_STATS)
    public Flux<Item> getTopFavorites() {
        log.info(LOG_MESSAGE_START_GET_TOP_FAVORITES);

        return itemService.getTopFavorites(Integer.valueOf(topFavorites))
                .doFinally(signalType -> log.info(LOG_MESSAGE_END_GET_TOP_FAVORITES));
    }

}
