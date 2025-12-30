package org.example.apitestingwitherrorthrowing.Controllers;

import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.example.apitestingwitherrorthrowing.Repositories.SongRepository;
import org.example.apitestingwitherrorthrowing.Services.AsyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("v1/songs")
public class SongController {

    AsyncService asyncService;

    public SongController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }


    @PostMapping
    public CompletableFuture<ResponseEntity<Song>> save(@RequestBody Song song) {
      return    asyncService.save(song).thenApply(ResponseEntity::ok);
    }

    @PostMapping("allsongs")
    public ResponseEntity<List<Song>> saveAllSongs( @RequestBody List<Song> songs) {
     List<Song> listOfSongs= asyncService.saveAllSongs(songs);
        return ResponseEntity.ok(listOfSongs);
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Song>>> findAllByMood(@RequestParam String mood) {
     return  asyncService.getAllSongsByMood(mood).thenApply(ResponseEntity::ok);
    }
    @GetMapping("allsongs")
    public ResponseEntity<List<Song>> findAll() {
        List<Song> listOfSongs = asyncService.getAllSongs();
        return ResponseEntity.ok(listOfSongs);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String songname) {
        asyncService.deleteSongByName(songname);
        return ResponseEntity.status(204).body("Song Deleted successfully");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Song song) {
        asyncService.updateSong(song);
        return ResponseEntity.status(200).body("Song Updated successfully");
    }

}
