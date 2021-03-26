package com.tts.UsersAPI2.Repository;

import com.tts.UsersAPI2.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  List<User> findAll();
  List<User> findByState(String state);
  Optional<User> findById(Long id);
}
