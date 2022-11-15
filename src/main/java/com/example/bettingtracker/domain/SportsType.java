package com.example.bettingtracker.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SportsType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sportsID;
	private String sportsName;

	public List<BetData> getBetData() {
		return betData;
	}

	public void setBetData(List<BetData> betData) {
		this.betData = betData;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sportsType")
	private List<BetData> betData;

	public SportsType() {
		super();
	}

	public SportsType(String sportsName) {
		super();
		this.sportsName = sportsName;
	}

	public Long getSportsID() {
		return sportsID;
	}

	public void setSportsID(Long sportsID) {
		this.sportsID = sportsID;
	}

	public String getSportsName() {
		return sportsName;
	}

	public void setSportsName(String sportsName) {
		this.sportsName = sportsName;
	}

	@Override
	public String toString() {
		return "SportsType [sportsID=" + sportsID + ", sportsName=" + sportsName + "]";
	}

}
