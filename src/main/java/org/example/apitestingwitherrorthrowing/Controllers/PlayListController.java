package org.example.apitestingwitherrorthrowing.Controllers;


import org.example.apitestingwitherrorthrowing.Dtos.PlayListCreateRequest;
import org.example.apitestingwitherrorthrowing.Entities.Playlist;
import org.example.apitestingwitherrorthrowing.Services.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("playlist")
public class PlayListController {

    PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> AllPlaylists = playListService.getAllPlayLists();
        return ResponseEntity.status(200).body(AllPlaylists);
    }

    @GetMapping("byname")
    public ResponseEntity<List<Playlist>> getPlaylistByName(@RequestParam String username) {
        List<Playlist> AllPlaylists = playListService.getPlayListsbyUser(username);
         return ResponseEntity.status(200).body(AllPlaylists);
    }

    @PostMapping
    public ResponseEntity<String> createPlaylist(@RequestBody PlayListCreateRequest playListCreateRequest) {
         playListService.addplaylist(playListCreateRequest);
        return ResponseEntity.status(201).body(" playlist saved succesfully");
    }

    @DeleteMapping
    public ResponseEntity<Playlist> deletePlaylist(@RequestBody Playlist playlist) {
        playListService.deleteplaylist(playlist);
        return ResponseEntity.status(204).body(playlist);
    }
}
