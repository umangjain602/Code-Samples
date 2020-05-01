package com.umang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.umang.all.User;
import com.umang.all.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableCaching
@Slf4j
public class RedisCachePocApplication implements CommandLineRunner{

	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RedisCachePocApplication.class, args);
	}

	@Autowired
	public RedisCachePocApplication(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}

	@Override
	public void run(String... strings) {
		// Populating embedded database here
		log.info("Saving users. Current user count is {}.", userRepository.count());
		User shubham = new User("Shubham", 2000);
		User pankaj = new User("Pankaj", 29000);
		User lewis = new User("Lewis", 550);

		userRepository.save(shubham);
		userRepository.save(pankaj);
		userRepository.save(lewis);
		log.info("Done saving users. Data: {}.", userRepository.findAll());
	}

}
