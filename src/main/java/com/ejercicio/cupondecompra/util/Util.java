package com.ejercicio.cupondecompra.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ejercicio.cupondecompra.util.Constant.*;

public class Util {

    public static String getIdsSeparatedByComma(List<String> itemIds) {
        String[] array = itemIds.toArray(String[]::new);
        return Arrays.toString(array)
                .replace(OPEN_SQUARE_BRACKET, BLANK_STRING)
                .replace(CLOSED_SQUARE_BRACKET, BLANK_STRING)
                .replaceAll(EMPTY_STRING, BLANK_STRING);
    }

    public static <T> List<T> findDuplicates(List<T> list) {
        Set<T> items = new HashSet<>();

        return list.stream()
                .filter(n -> !items.add(n))
                .toList();
    }

}
