package com.example.bettingtracker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bettingtracker.web.BettingTrackerController;
import com.example.bettingtracker.web.CrudController;
import com.example.bettingtracker.web.UsersController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BettingtrackerApplicationTests {

	@Autowired
	private UsersController ucontroller;

	@Autowired
	private CrudController ccontroller;

	@Autowired
	private BettingTrackerController bcontroller;

	@Test // Requires database credentials
	public void contexLoads() throws Exception {
		assertThat(ucontroller).isNotNull();
		assertThat(ccontroller).isNotNull();
		assertThat(bcontroller).isNotNull();
	}

}
