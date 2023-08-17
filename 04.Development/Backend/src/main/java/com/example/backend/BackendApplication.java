package com.example.backend;

import com.example.backend.enums.RoleEnum;
import com.example.backend.securities.user.Role;
import com.example.backend.securities.user.RoleRepository;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) throws Exception {
		List<User> users = userRepository.findAll();
		if(!users.isEmpty()) return (String[] args) -> {};
		return (String[] args) -> {
			Role roleAdmin = new Role(RoleEnum.ROLE_ADMIN);
			roleRepository.save(roleAdmin);
			Role roleUser = new Role(RoleEnum.ROLE_USER);
			roleRepository.save(roleUser);

			User admin = new User("admin",
					passwordEncoder.encode("123"),
					"tamthanhbui01@gmail.com",
					LocalDateTime.now(),
					LocalDateTime.now(),
					roleAdmin);
			userRepository.save(admin);
		};
	}

}
