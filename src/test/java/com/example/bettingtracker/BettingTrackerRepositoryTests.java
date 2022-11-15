package com.example.bettingtracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bettingtracker.domain.BetData;
import com.example.bettingtracker.domain.BetDataRepository;
import com.example.bettingtracker.domain.Bookmaker;
import com.example.bettingtracker.domain.BookmakerRepository;
import com.example.bettingtracker.domain.Deposit;
import com.example.bettingtracker.domain.DepositRepository;
import com.example.bettingtracker.domain.SportsType;
import com.example.bettingtracker.domain.SportsTypeRepository;
import com.example.bettingtracker.domain.Users;
import com.example.bettingtracker.domain.UsersRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BettingTrackerRepositoryTests {

	@Autowired
	private UsersRepository urepository;

	@Autowired
	private BookmakerRepository bmrepository;

	@Autowired
	private SportsTypeRepository srepository;

	@Autowired
	private DepositRepository deporepository;

	@Autowired
	private BetDataRepository betrepository;

	// All tests require postgres credentials

	// SPRING_DATASOURCE_URL:
	// jdbc:postgresql://ec2-176-34-215-248.eu-west-1.compute.amazonaws.com:5432/d2c55fgojahetf?sslmode=require
	// SPRING_DATASOURCE_PASSWORD:
	// 2032b6637a6527dbb5eadc81155c12daec488f89d2dd8127a9119c98a3442db4
	// SPRING_DATASOURCE_USERNAME: ywulpdipxbugcx

	@Test // Tests that new user is created
	public void createNewUser() {
		Users user = new Users("testuser", "$2a$12$pUtNOmALITaE5BFJDy4IFee9mBNwGi3u/LZCYLtDcYcI9X5qb21Eq",
				"testuser@test.com", "USER");
		urepository.save(user);
		Users newuser = urepository.findByEmail("testuser@test.com");
		assertThat(newuser.getUserID()).isNotNull();
		assertThat(user.getEmail()).isEqualTo(newuser.getEmail());
	}

	@Test
	public void deleteNewUser() {
		Users user = urepository.findByUsername("testuser");
		assertThat(user.getUserID()).isNotNull();
		urepository.delete(user);
		assertThat(urepository.findByUsername("testuser")).isNull();

	}

	@Test
	public void createNewBookmaker() {
		Bookmaker bookie = new Bookmaker("Testbookie");
		bmrepository.save(bookie);
		assertThat(bookie.getBookmakerID()).isNotNull();
		assertThat(bmrepository.findByBookmakerName("Testbookie")).isNotNull();
	}

	@Test
	public void deleteNewBookmaker() {
		Bookmaker bookie = bmrepository.findByBookmakerName("Testbookie").get(0);
		assertThat(bookie.getBookmakerID()).isNotNull();
		bmrepository.delete(bookie);
		assertThat(bmrepository.findByBookmakerName("Testbookie").isEmpty());
	}

	@Test
	public void createNewSport() {
		SportsType sport = new SportsType("testisportti");
		srepository.save(sport);
		assertThat(sport.getSportsID()).isNotNull();
		assertThat(srepository.findBySportsName("testisportti")).isNotNull();
	}

	@Test
	public void deleteNewSport() {
		SportsType sport = srepository.findBySportsName("testisportti").get(0);
		assertThat(sport.getSportsID()).isNotNull();
		srepository.delete(sport);
		assertThat(srepository.findBySportsName("testisportti").isEmpty());
	}

	@Test
	public void createNewDeposit() {
		Users user = urepository.findByUsername("user1");
		Deposit deposit = new Deposit(999.9, user);
		deporepository.save(deposit);
		assertThat(deposit.getDepositID()).isNotNull();
		assertThat(deporepository.findByUsers(user).get(0)).isNotNull();
	}

	@Test
	public void deleteNewDeposit() {

		Users user = urepository.findByUsername("user1");
		List<Deposit> list = deporepository.findByUsers(user);
		Deposit deposit = deporepository.findByAmount(999.9).get(0);
		Deposit byuser = deporepository.findByUsers(user).get(list.size() - 1);
		assertThat(deposit.getDepositID()).isNotNull();
		assertThat(deposit.equals(byuser));
		deporepository.delete(deposit);
		assertThat(deporepository.findByAmount(999.9)).isEmpty();
	}

	@Test
	public void createNewBetData() {

		BetData betdata = new BetData();
		betdata.setBetType("testityyppi");
		betrepository.save(betdata);
		assertThat(betrepository.findByBetType("testityyppi")).isNotNull();

	}

	@Test
	public void deleteNewBetData() {

		BetData betdata = betrepository.findByBetType("testityyppi");
		betrepository.delete(betdata);
		assertThat(betrepository.findByBetType("testityyppi")).isNull();

	}

}