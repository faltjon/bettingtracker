package com.example.bettingtracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookmakerRepository extends CrudRepository<Bookmaker, Long> {

	List<Bookmaker> findByBookmakerName(String bookmakerName);
}
