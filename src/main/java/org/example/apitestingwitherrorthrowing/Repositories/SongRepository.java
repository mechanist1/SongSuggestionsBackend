package org.example.apitestingwitherrorthrowing.Repositories;

import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends CrudRepository<Song, Long> {


    List<Song> findAllByMood(String mood);

    void deleteSongByName(String name);
}

