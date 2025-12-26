package org.example.apitestingwitherrorthrowing.Repositories;

import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    List<Song> findAllBy(String title);

    List<Song> findAllByMood(String mood);
}

