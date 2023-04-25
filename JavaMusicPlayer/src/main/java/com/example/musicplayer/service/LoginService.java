package com.example.musicplayer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicplayer.model.Login;
import com.example.musicplayer.repository.LoginRepository;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repo;

    public void save(Login lng) {
        repo.save(lng);
    }

    public Optional<Login> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
