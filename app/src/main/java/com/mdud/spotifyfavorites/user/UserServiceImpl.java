package com.mdud.spotifyfavorites.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public User addUser(String spotifyUserId) {
        if(checkIfUserExists(spotifyUserId)) {
            throw new UserAlreadyExistsException("user already exists");
        }

        User newUser = new User(spotifyUserId, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        return userRepository.save(newUser);
    }

    @Override
    public User update(User user) {
        if(!checkIfUserExists(user.getSpotifyUserId())) {
            throw new NoSuchElementException("user with this id don't exists");
        }

        return userRepository.save(user);
    }
}
