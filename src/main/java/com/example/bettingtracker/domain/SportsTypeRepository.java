package com.example.bettingtracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SportsTypeRepository extends CrudRepository<SportsType, Long> {

	List<SportsType> findBySportsName(String sportsName);

}
