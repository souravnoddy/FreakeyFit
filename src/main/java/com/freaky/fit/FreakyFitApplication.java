package com.freaky.fit;

import com.freaky.fit.entities.UserDetails;
import com.freaky.fit.reositories.UserDetailsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class FreakyFitApplication implements CommandLineRunner {
	@Autowired
	private UserDetailsrepository userDetailsrepository;

	public static void main(String[] args) {
		SpringApplication.run(FreakyFitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*userDetailsrepository.save(UserDetails.builder()
				.id(UUID.randomUUID().toString())
				.name("Sourav")
				.address("wesx")
				.phoneNumber("785")
				.build());*/
	}
}
