package org.example.apitestingwitherrorthrowing.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.example.apitestingwitherrorthrowing.Exceptions.BusinessException;
import org.example.apitestingwitherrorthrowing.Repositories.SongRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Slf4j
@Service
@AllArgsConstructor
public class AsyncService {

    SongRepository songRepository;

    @Async
    public CompletableFuture<Song> save(Song song) {
        System.out.println(song);
        songRepository.save(song);
        return CompletableFuture.completedFuture(song);
    }

    @Async
    public CompletableFuture<List<Song>> getAllSongsByMood(String mood) {
        List<Song> songLists= songRepository.findAllByMood(mood);
        if(songLists.isEmpty()){
            throw new BusinessException("there aren't any songs");
        }
        System.out.println("getAllSongsByMood: " + songLists);
        return CompletableFuture.completedFuture(songLists);
    }
}
