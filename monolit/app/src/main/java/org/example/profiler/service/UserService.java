package org.example.profiler.service;

import org.example.carter.model.User;
import org.example.carter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> all() { return userRepository.findAll(); }

    public Optional<User> get(Long id) { return userRepository.findById(id); }

    public User create(User user) { return userRepository.save(user); }

    public Optional<User> update(Long id, User update) {
        return userRepository.findById(id).map(existing -> {
            existing.setName(update.getName());
            existing.setEmail(update.getEmail());
            return userRepository.save(existing);
        });
    }

    public void delete(Long id) { userRepository.deleteById(id); }
}