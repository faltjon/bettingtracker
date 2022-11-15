package com.example.bettingtracker.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long depositID;
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "userID")
	private Users users;

	public Deposit() {
		super();
	}

	public Deposit(Double amount, Users users) {
		super();
		this.amount = amount;
		this.users = users;
	}

	public Long getDepositID() {
		return depositID;
	}

	public void setDepositID(Long depositID) {
		this.depositID = depositID;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Deposit [depositID=" + depositID + ", amount=" + amount + ", users=" + users + "]";
	}

}
