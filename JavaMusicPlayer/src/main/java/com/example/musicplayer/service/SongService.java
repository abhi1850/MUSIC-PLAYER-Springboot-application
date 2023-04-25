package com.example.musicplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicplayer.model.Song;
import com.example.musicplayer.repository.SongRepository;

@Service
public class SongService {
    @Autowired
    private SongRepository repo;

    public List<Song> listAll() {
        return repo.findAll();
    }

    public void save(Song std) {
        repo.save(std);
    }

    public Song get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public List<Song> findByTitle(String title) {
        //return repo.findByUsername(username);
        return repo.findByTitle(title);
    }
}