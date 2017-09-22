package com.zhaowq.java8.chapter03;

import com.zhaowq.java8.chapter01.Album;
import com.zhaowq.java8.chapter01.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhaowq on 2017/9/12.
 */
public class Exercises {
    int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, element) -> acc + element);
    }

    List<String> getArtistInfo(List<Artist> artists) {
        return artists.stream().map(artist -> artist.getName() + ":" + artist.getNationality()).collect(Collectors.toList());
    }

    List<Album> getAlbum(List<Album> albums) {
        return albums.stream().filter(album -> album.getTrackList().size() <= 3).collect(Collectors.toList());
    }

    int exe2(List<Artist> artists) {
        return artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, (acc, count) -> acc + count).intValue();
    }

    static int countLowercaseLetters(String str) {
        return (int) str.chars().filter(Character::isLowerCase).count();
    }

    static Optional<String> mostLowercaseString(List<String> list) {
        return list.stream().max(Comparator.comparing(Exercises::countLowercaseLetters));
    }
}
