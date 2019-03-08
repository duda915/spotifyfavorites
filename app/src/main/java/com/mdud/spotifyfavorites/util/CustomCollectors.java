package com.mdud.spotifyfavorites.util;

import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollectors {

    public static <T> Collector<T, ?, T> singleElementCollector() {
        return Collectors.collectingAndThen(Collectors.toList(), list -> {
            if(list.size() != 1) {
                throw new NoSuchElementException("cannot find this element");
            }
            return list.get(0);
        });
    }
}
