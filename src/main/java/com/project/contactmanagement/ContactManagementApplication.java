package com.project.contactmanagement;

import com.project.contactmanagement.entity.UserInfo;
import com.project.contactmanagement.repository.UserInfoRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
@SecurityScheme(name = "contact-management-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ContactManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner loadData(UserInfoRepository userInfoRepository) {
		log.info("Adding default users during application start...");
		return args ->{
			UserInfo user1 = new UserInfo();
			user1.setId(1L);
			user1.setName("Admin");
			user1.setEmail("admin@gmail.com");
			user1.setPassword(passwordEncoder.encode("Admin123"));
			user1.setRoles("ADMIN");

			userInfoRepository.save(user1);

			UserInfo user2 = new UserInfo();
			user2.setId(2L);
			user2.setName("User");
			user2.setEmail("user@gmail.com");
			user2.setPassword(passwordEncoder.encode("User123"));
			user2.setRoles("USER");

			userInfoRepository.save(user2);
		};
	}
}
