package com.umang.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {


  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
  @GetMapping("/{userId}")
  public User getUser(@PathVariable Long userId) throws NumberFormatException, Exception {
    log.info("Getting user with ID {}.", userId);
    
    return userRepository.findById(userId).orElseThrow(()->new Exception("id not found"));
  }
  @CachePut(value = "users", key = "#user.id")
  @PutMapping("/update")
  public User updatePersonByID(@RequestBody User user) {
    userRepository.save(user);
    return user;
  }
  @CacheEvict(value = "users", allEntries=true)
  @DeleteMapping("/{id}")
  public void deleteUserByID(@PathVariable Long id) {
    log.info("deleting person with id {}", id);
    userRepository.deleteById(id);
  }

}