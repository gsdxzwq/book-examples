package com.zhaowq.java8.chapter05;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhaowq on 2017/9/15.
 */
public class Exercises {
    public static void main(String[] args) {
        //2.a
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        System.out.println(names.collect(Collectors.maxBy(Comparator.comparing(String::length))));
        //System.out.print(names.reduce());

        //2.b
        Stream<String> namesB = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");
        System.out.println(namesB.collect(Collectors.groupingBy(name -> name, Collectors.counting())));

        //3

    }
}
