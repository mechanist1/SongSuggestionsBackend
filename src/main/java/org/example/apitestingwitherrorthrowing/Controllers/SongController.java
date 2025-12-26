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

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Song>>> findAllByMood(@RequestParam String mood) {
     return  asyncService.getAllSongsByMood(mood).thenApply(ResponseEntity::ok);
    }

}
