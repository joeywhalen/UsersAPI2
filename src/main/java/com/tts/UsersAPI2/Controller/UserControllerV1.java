package com.tts.UsersAPI2.Controller;

import com.tts.UsersAPI2.Model.User;
import com.tts.UsersAPI2.Repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "users", description = "Methods for Creating, Getting, Updating, and Deleting users")
@RestController
@RequestMapping("/v1")
public class UserControllerV1 {

  @Autowired
  private UserRepository userRepository;

  @ApiOperation(value = "Endpoint to get all users", response = User.class, responseContainer = "List")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Got all users")})
  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers(@RequestParam(value = "state", required = false) String state) {
    if (state != null) {
      return new ResponseEntity<>((List<User>) userRepository.findByState(state), HttpStatus.OK);
    }
    return new ResponseEntity<>((List<User>) userRepository.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint to get a single user by id", response = User.class)
  @ApiResponses(value = {@ApiResponse(code = 404, message = "User with the given id does not exist"),
                        @ApiResponse(code = 200, message = "Got user by specific id")})
  @GetMapping("/users/{id}")
  public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") Long id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint to create a new user")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "There was a validation error"),
                        @ApiResponse(code = 201, message = "New user has been created")})
  @PostMapping("/users")
  public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Endpoint to update a user")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "User with the given id does not exist"),
                        @ApiResponse(code = 400, message = "There was a validation error"),
                        @ApiResponse(code = 200, message = "User has been updated")})
  @PutMapping("/users/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user,
                                         BindingResult bindingResult) {
    Optional<User> existingUser = userRepository.findById(id);
    if (!existingUser.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint to delete an existing user", response = User.class)
  @ApiResponses(value = {@ApiResponse(code = 404, message = "User with the given id does not exist"),
                        @ApiResponse(code = 200, message = "User has been deleted")})
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
    Optional<User> existingUser = userRepository.findById(id);
    if (!existingUser.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
