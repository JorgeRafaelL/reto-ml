package com.ejercicio.cupondecompra.exception;

import com.ejercicio.cupondecompra.model.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ejercicio.cupondecompra.util.Constant.*;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<?>> handleBadRequestException(WebExchangeBindException webExchangeBindException) {

        List<String> errors = new ArrayList<>();
        webExchangeBindException.getFieldErrors()
                .forEach(err -> errors.add(
                        err.getField().concat(COLON_PLUS_SPACE)
                                .concat(Objects.requireNonNull(err.getDefaultMessage()))));
        webExchangeBindException.getGlobalErrors()
                .forEach(err -> errors.add(
                        err.getObjectName().concat(COLON_PLUS_SPACE)
                                .concat(Objects.requireNonNull(err.getDefaultMessage()))));

        ResponseException responseException = ResponseException.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(webExchangeBindException.getMessage())
                .detail(errors)
                .build();
        return Mono.just(new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({DuplicateItemIdsExcpetion.class})
    public Mono<ResponseEntity<?>> duplicateItemIds(DuplicateItemIdsExcpetion duplicateItemIdsExcpetion) {
        ResponseException responseException = ResponseException.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .message(duplicateItemIdsExcpetion.getMessage())
                .detail(duplicateItemIdsExcpetion.getCause().getMessage())
                .build();
        return Mono.just(new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({NotItemsFoundForAmountGivenException.class})
    public Mono<ResponseEntity<?>> notFoundItemsML(NotItemsFoundForAmountGivenException notItemsFoundForAmountGivenException) {
        ResponseException responseException = ResponseException.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .message(notItemsFoundForAmountGivenException.getMessage())
                .detail(DETAIL_NOT_FOUND_ITEMS_AT_ML_API_MESSAGE)
                .build();
        return Mono.just(new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND));
    }


    @ExceptionHandler({Exception.class})
    public Mono<ResponseEntity<?>> handleAll(Exception exception) {
        boolean is400Error = exception.getMessage().contains(STATUS_400_CODE);

        ResponseException responseException = ResponseException.builder()
                .code(is400Error ? HttpStatus.BAD_REQUEST.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(is400Error ? HttpStatus.BAD_REQUEST.name() : HttpStatus.INTERNAL_SERVER_ERROR.name())
                .message(exception.getLocalizedMessage())
                .detail(exception.getMessage())
                .build();
        return Mono.just(new ResponseEntity<>(responseException, is400Error
                ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
