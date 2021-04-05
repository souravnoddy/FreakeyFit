package com.freaky.fit;

import com.freaky.fit.documents.UserDetails;
import com.freaky.fit.repositories.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.UUID;

@SpringBootApplication
@Slf4j
public class FreakyFitApplication implements CommandLineRunner {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(FreakyFitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userDetailsRepository.save(UserDetails.builder()
				.id(UUID.randomUUID().toString())
				.name("Sourav")
				.address("hjb")
				.phoneNumber("96")
				.build());
		log.info("Saved");
	}
}
