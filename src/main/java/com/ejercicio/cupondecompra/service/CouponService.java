package com.ejercicio.cupondecompra.service;

import com.ejercicio.cupondecompra.document.Item;
import com.ejercicio.cupondecompra.model.ApiMLResponse;
import com.ejercicio.cupondecompra.model.Request;
import com.ejercicio.cupondecompra.model.Response;
import com.ejercicio.cupondecompra.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.paukov.combinatorics3.Generator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Slf4j
@Service
public class CouponService {
    private final WebClient webClient;
    private final ItemService itemService;
    @Value(ITEMS_URL_MERCADO_LIBRE)
    private String itemsUrl;

    public CouponService(WebClient webClient, ItemService itemService) {
        this.webClient = webClient;
        this.itemService = itemService;
    }


    private Flux<ApiMLResponse> getDetailItems(Request request) {
        log.info(LOG_MESSAGE_START_GETTING_ITEMS_FROM_ML_API);

        String itemIdsParameter = Util.getIdsSeparatedByComma(request.getItemIds());
        return this.webClient.get().uri(uriBuilder -> uriBuilder
                        .path(itemsUrl)
                        .queryParam(IDS_QUERY_PARAM, itemIdsParameter)
                        .queryParam(ATTRIBUTES_QUERY_PARAM, ATTRIBUTES_PARAM)
                        .build())
                .retrieve().bodyToFlux(ApiMLResponse.class)
                .doOnComplete(() -> log.info(LOG_MESSAGE_END_GETTING_ITEMS_FROM_ML_API))
                .onErrorMap(throwable -> new RuntimeException(
                        EXCEPTION_MESSAGE_API_ML_ERROR.concat(throwable.getMessage())));
    }

    public Mono<Optional<?>> getMaxSpendPossible(Request request) {
        Map<List<String>, Float> mapResult = new HashMap<>();
        log.info(LOG_MESSAGE_START_GETTING_OPTIMUS_TOTAL);

        return getDetailItems(request)
                .filter(apiMLResponse -> apiMLResponse.getCode().equals(200))
                .map(apiMLResponse -> itemService.updateOrInsertItem(apiMLResponse.getItem()))
                .flatMap(itemMono -> itemMono.map(item -> item))
                .collectList()
                .map(itemList -> Generator
                        .subset(itemList)
                        .simple()
                        .stream()
                        .filter(items -> !items.isEmpty())
                        .peek(items -> {
                            AtomicReference<Float> finalTotal = new AtomicReference<>(0F);
                            items.forEach(item -> finalTotal.updateAndGet(v -> v + item.getPrice()));
                            if (finalTotal.get() <= request.getAmount())
                                mapResult.put(items.stream().map(Item::getItemId).collect(Collectors.toList()), finalTotal.get());
                        })
                        .toList())
                .map(lists -> {
                    if (!mapResult.isEmpty())
                        return Optional.of(Response.builder().itemIds(Collections.max(mapResult.entrySet(), Map.Entry.comparingByValue()).getKey())
                                .total(Collections.max(mapResult.entrySet(), Map.Entry.comparingByValue()).getValue()).build());
                    return Optional.empty();
                })
                .doOnSuccess(o -> log.info(LOG_MESSAGE_END_GETTING_OPTIMUS_TOTAL))
                .onErrorMap(throwable -> new RuntimeException(
                        EXCEPTION_MESSAGE_GETTING_OPTIMUS_TOTAL_ERROR.concat(throwable.getMessage())));
    }

}
