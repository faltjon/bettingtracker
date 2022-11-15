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
public class Bookmaker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookmakerID;
	private String bookmakerName;

	public List<BetData> getBetData() {
		return betData;
	}

	public void setBetData(List<BetData> betData) {
		this.betData = betData;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookmaker")
	private List<BetData> betData;

	public Bookmaker() {
		super();
	}

	public Bookmaker(String bookmakerName) {
		super();
		this.bookmakerName = bookmakerName;
	}

	public Long getBookmakerID() {
		return bookmakerID;
	}

	public void setBookmakerID(Long bookmakerID) {
		this.bookmakerID = bookmakerID;
	}

	public String getBookmakerName() {
		return bookmakerName;
	}

	public void setBookmakerName(String bookmakerName) {
		this.bookmakerName = bookmakerName;
	}

	@Override
	public String toString() {
		return "Bookmaker [bookmakerID=" + bookmakerID + ", bookmakerName=" + bookmakerName + "]";
	}

}
