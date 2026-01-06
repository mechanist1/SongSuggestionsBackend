package org.example.apitestingwitherrorthrowing.Services;

import lombok.extern.slf4j.Slf4j;
import org.example.apitestingwitherrorthrowing.Dtos.PlayListCreateRequest;
import org.example.apitestingwitherrorthrowing.Entities.Playlist;
import org.example.apitestingwitherrorthrowing.Entities.Song;
import org.example.apitestingwitherrorthrowing.Entities.User;
import org.example.apitestingwitherrorthrowing.Exceptions.BusinessException;
import org.example.apitestingwitherrorthrowing.Repositories.PlayListRepository;
import org.example.apitestingwitherrorthrowing.Repositories.SongRepository;
import org.example.apitestingwitherrorthrowing.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlayListService {

    PlayListRepository playListrepository;
    UserRepository userRepository;
    SongRepository songRepository;

    public PlayListService(PlayListRepository playListRepository, UserRepository userRepository, SongRepository songRepository)
    {
        this.playListrepository = playListRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public List<Playlist> getAllPlayLists() {
        List<Playlist> allplaylists = playListrepository.findAll();
        if(allplaylists.isEmpty()) {
           throw new BusinessException("there aren't any playlists");
        }
        return allplaylists;
    }

    public List<Playlist> getPlayListsbyUser(String Username) {
        List<Playlist> playlists = playListrepository.findByUserName(Username);
        if(playlists.isEmpty()) {
            throw new BusinessException("there aren't any playlists for the user"+Username);
        }
        return playlists;
    }
    public void addplaylist(PlayListCreateRequest playListCreateRequest) {
        Optional<User> user = userRepository.findById(playListCreateRequest.getUserId());
        if(user.isEmpty()){
            throw new BusinessException("there aren't any playlists related to the user");
        }
        List<Song> listSong = new ArrayList<>();
        List<Long> songIds = playListCreateRequest.getSongIds();
        for(Long id : songIds) {

                Optional<Song> song =songRepository.findById(id);
                if(song.isPresent()) {
                    listSong.add(song.get());
                }
                else{
                log.error("this song with the id "+id+" does not exist");
                }
        }
        Playlist playlist = new Playlist();
        playlist.setUser(user.get());
        playlist.setSongs(listSong);
        playlist.setName(playListCreateRequest.getName());

             playListrepository.save(playlist);
    }


    public void deleteplaylist(Playlist playlist) {
        playListrepository.delete(playlist);
    }
}
