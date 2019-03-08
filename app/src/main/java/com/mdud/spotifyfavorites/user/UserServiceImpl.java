package com.mdud.spotifyfavorites.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean checkIfUserExists(String spotifyId) {
        return userRepository.findBySpotifyUserId(spotifyId).isPresent();
    }

    @Override
    public User findUser(String spotifyId) {
        return userRepository.findBySpotifyUserId(spotifyId).orElseThrow(() -> new NoSuchElementException("user with this id don't exists"));
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
