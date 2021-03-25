package com.tts.UsersAPI2.Controller;

import com.tts.UsersAPI2.Model.User;
import com.tts.UsersAPI2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getUsers(@RequestParam(value = "state", required = false) String state) {
    if (state != null) {
      return (List<User>) userRepository.findByState(state);
    }
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
    return userRepository.findById(id);
  }

  @PostMapping("/users")
  public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/users/{id}")
  public void updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
    userRepository.save(user);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable(value = "id") Long id) {
    userRepository.deleteById(id);
  }
}
