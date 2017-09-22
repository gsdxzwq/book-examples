package com.zhaowq.java8.chapter04;

import com.zhaowq.java8.chapter01.Artist;

import java.util.List;
import java.util.Optional;

/**
 * Created by zhaowq on 2017/9/14.
 */
public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName).orElse("unknown");
    }
}
