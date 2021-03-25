package com.tts.UsersAPI2.Controller;

import com.tts.UsersAPI2.Model.User;
import com.tts.UsersAPI2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/user")
  public List<User> getUsers(@RequestParam(value = "state", required = false) String state) {
    if (state != null) {
      return (List<User>) userRepository.findByState(state);
    }
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
    return userRepository.findById(id);
  }

  @PostMapping("/user")
  public void createUser(@RequestBody User user) {
    userRepository.save(user);
  }

  @PutMapping("/user/{id}")
  public void createUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
    userRepository.save(user);
  }

  @DeleteMapping("/user/{id}")
  public void createUser(@PathVariable(value = "id") Long id) {
    userRepository.deleteById(id);
  }
}
