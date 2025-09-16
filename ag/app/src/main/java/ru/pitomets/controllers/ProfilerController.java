package ru.pitomets.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pitomets.models.User;
import ru.pitomets.services.ProfilerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfilerController {
    private final ProfilerService profilerService;

    public ProfilerController(ProfilerService profilerService) {
        this.profilerService = profilerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(profilerService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{profile_id}")
    public ResponseEntity<User> getUserById(@PathVariable long profile_id) {
        User user = profilerService.getUserById(profile_id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestPart User userReceived) {
        try {
            User user = profilerService.addUser(userReceived);
            return new ResponseEntity<>(userReceived, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long profile_id) {
        User user = profilerService.getUserById(profile_id);
        if (user != null) {
            profilerService.deleteUser(profile_id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{profile_id}")
    public ResponseEntity<?> updateUser(@PathVariable long profile_id,
                                        @RequestPart User userReceived) {
        User user = null;
        user = profilerService.updateUser(profile_id, userReceived);
        return user != null ? new ResponseEntity<>("updated", HttpStatus.OK) :
                new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(/* uri */)
    public ResponseEntity<List<User>> findUser(/* че-то принимать */) {
        List<User> found = profilerService.findUser();
        return found != null ? new ResponseEntity<>(found, HttpStatus.FOUND) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
