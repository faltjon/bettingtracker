package com.example.bettingtracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

@SpringBootApplication
public class BettingtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BettingtrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BetDataRepository betrepository, SportsTypeRepository srepository,
			BookmakerRepository bmRepository, DepositRepository drepository, UsersRepository urepository) {
		return (args) -> {

			srepository.save(new SportsType("Esports"));
			SportsType testsport = srepository.findBySportsName("Esports").get(0);
			System.out.println(testsport);
			bmRepository.save(new Bookmaker("Coolbet"));
			Bookmaker testbm = bmRepository.findByBookmakerName("Coolbet").get(0);
			System.out.println(testbm);

			Users user1 = new Users("user1", "$2a$10$DLLFGBYp13nA2ffADpwkZOHmpMiP2FjDOYdImFCcKjEg9zj59FCAu",
					"user1@user.com", "USER");
			Users user2 = new Users("user2", "$2a$10$jGrHQbpH/d5Af/RMVuLg9Oqtp9Nfv3QHzZNWPOZGqYhoxL2LtyJZi",
					"user2@user.com", "USER");
			urepository.save(user1);
			urepository.save(user2);

			drepository.save(new Deposit(50.0, user1));
			drepository.save(new Deposit(200.0, user1));
			drepository.save(new Deposit(100.0, user2));
			drepository.save(new Deposit(150.0, user2));

			BetData testi = new BetData("user1", "team2", "Team 1 wins", "2.2", "100", "1", 120, testsport, testbm,
					user1);
			betrepository.save(testi);
			BetData testi2 = new BetData("user2", "team2", "Team 1 wins", "2.2", "100", "1", 120, testsport, testbm,
					user2);
			betrepository.save(testi2);
			System.out.println(testi);
			System.out.println(testi2);
		};

	}
}
