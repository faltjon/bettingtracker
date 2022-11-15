package com.example.bettingtracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {

	List<Deposit> findByAmount(Double amount);

	List<Deposit> findByUsers(Users user);

}
