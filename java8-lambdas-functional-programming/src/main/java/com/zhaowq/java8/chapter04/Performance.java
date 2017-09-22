package com.zhaowq.java8.chapter04;

import com.zhaowq.java8.chapter01.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * 该接口表示艺术家的演出——专辑或演唱会
 * Created by zhaowq on 2017/9/14.
 */
public interface Performance {
    String getName();
    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        return this.getMusicians().flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }
}
