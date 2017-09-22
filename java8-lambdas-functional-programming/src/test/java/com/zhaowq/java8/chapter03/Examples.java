package com.zhaowq.java8.chapter03;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingDouble;
import static org.junit.Assert.assertEquals;

/**
 * Created by zhaowq on 2017/9/12.
 */
public class Examples {
    @Test
    public void test() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), collected);
    }

    @Test
    public void testReduce() {
        Integer total = Stream.of(1, 2, 3, 4).reduce(0, (acc, element) -> acc + element);
        System.out.print(total);
    }


}
