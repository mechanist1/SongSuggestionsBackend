package org.example.apitestingwitherrorthrowing.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.example.apitestingwitherrorthrowing.Exceptions.BusinessException;
import org.example.apitestingwitherrorthrowing.Repositories.SongRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    public List<Song> saveAllSongs(List<Song> songs) {
        try {
            songRepository.saveAll(songs);
        }
        catch (Exception e) {
            log.error(e.getMessage()+"there was an error saving all the songs");
            throw new BusinessException(e.getMessage()+"there was an error saving all the songs");
        }
        return songs;
    }

    public List<Song> getAllSongs() {
        List<Song> listOfSongs;
        try {
            listOfSongs = (List<Song>) songRepository.findAll();
        }
        catch (Exception e) {
            log.error(e.getMessage()+"there was an error getting all the songs");
            throw new BusinessException(e.getMessage()+"there was an error getting all the songs");
        }
        return listOfSongs;
    }
    public void deleteSongByName(String songName) {
        try {
            songRepository.deleteSongByName(songName);
            log.info(songName + " deleted successfully");
        }
        catch (Exception e) {
            log.error(e.getMessage()+"there was an error deleting the song");
            throw new BusinessException(e.getMessage()+"there was an error deleting the song named"+songName);
        }
    }

    public Song updateSong(Song song) {
        Long id = song.getId();

        Song existing = songRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Song not found with id " + id));

        existing.setName(song.getName());
        existing.setArtist(song.getArtist());
        existing.setGenre(song.getGenre());
        existing.setMood(song.getMood());
        existing.setReleaseDate(song.getReleaseDate());

        return songRepository.save(existing);
    }

}
