package com.example.bettingtracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BetDataRepository extends CrudRepository<BetData, Long> {

	List<BetData> findByUsers(Users users);

	BetData findByTeam1(String team1);

	BetData findByBetType(String bettype);
}
