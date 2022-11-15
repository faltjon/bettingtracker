package com.example.bettingtracker.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BetData {

	// Generate ID for BetData entity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long betDataID;
	private String team1, team2, betType, multiplier, wager, result;
	private double bankChange;

	@ManyToOne
	@JoinColumn(name = "sportsID")
	private SportsType sportsType;

	@ManyToOne
	@JoinColumn(name = "bookmakerID")
	private Bookmaker bookmaker;

	@ManyToOne
	@JoinColumn(name = "userID")
	private Users users;

	public BetData() {
		super();
	}

	// BetData constructor
	public BetData(String team1, String team2, String betType, String multiplier, String wager, String result,
			double bankChange, SportsType sportsType, Bookmaker bookmaker, Users users) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.betType = betType;
		this.multiplier = multiplier;
		this.wager = wager;
		this.result = result;
		this.bankChange = bankChange;
		this.sportsType = sportsType;
		this.bookmaker = bookmaker;
		this.users = users;

	}

	public Long getBetDataID() {
		return betDataID;
	}

	public void setBetDataID(Long betDataID) {
		this.betDataID = betDataID;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public String getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	public String getWager() {
		return wager;
	}

	public void setWager(String wager) {
		this.wager = wager;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public double getBankChange() {
		return bankChange;
	}

	public void setBankChange(double bankChange) {
		this.bankChange = bankChange;
	}

	public SportsType getSportsType() {
		return sportsType;
	}

	public void setSportsType(SportsType sportsType) {
		this.sportsType = sportsType;
	}

	public Bookmaker getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(Bookmaker bookmaker) {
		this.bookmaker = bookmaker;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "BetData [betDataID=" + betDataID + ", team1=" + team1 + ", team2=" + team2 + ", betType=" + betType
				+ ", multiplier=" + multiplier + ", wager=" + wager + ", result=" + result + ", bankChange="
				+ bankChange + ", sportsType=" + sportsType + ", bookmaker=" + bookmaker + ", users=" + users + "]";
	}

}
