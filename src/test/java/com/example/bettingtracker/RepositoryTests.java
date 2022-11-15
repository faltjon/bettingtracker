package com.example.bettingtracker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bettingtracker.domain.Users;
import com.example.bettingtracker.domain.UsersRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTests {

	@Autowired
	private UsersRepository urepository;

	@Test
	public void createNewUser() {
		Users user = new Users("testuser", "$2a$12$pUtNOmALITaE5BFJDy4IFee9mBNwGi3u/LZCYLtDcYcI9X5qb21Eq",
				"testuser@test.com", "USER");
		urepository.save(user);
		Users newuser = urepository.findByEmail("testuser@test.com");

		assertThat(user.getEmail()).isEqualTo(newuser.getEmail());
	}
}