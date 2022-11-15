package com.example.bettingtracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bettingtracker.domain.BetData;
import com.example.bettingtracker.domain.BetDataRepository;
import com.example.bettingtracker.domain.Deposit;
import com.example.bettingtracker.domain.DepositRepository;
import com.example.bettingtracker.domain.Users;
import com.example.bettingtracker.domain.UsersRepository;

@Controller
public class BettingTrackerController {

	@Autowired
	private BetDataRepository betrepository;

	@Autowired
	private DepositRepository depoRepository;

	@Autowired
	private UsersRepository urepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listQuestions(Model model) {

		// Finds current user to list their bets. Calculates all wins/losses and deposit
		// amount.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users curruser = urepository.findByUsername(username);

		List<Deposit> depositslist = depoRepository.findByUsers(curruser);
		List<BetData> winlosslist = betrepository.findByUsers(curruser);

		Double alldeposits = 0.0;
		for (int i = 0; i < depositslist.size(); i++) { // Sums deposits
			alldeposits += depositslist.get(i).getAmount();
		}

		Double allwins = 0.0;
		for (int i = 0; i < winlosslist.size(); i++) { // Sums all BetData BankChange rows
			allwins += winlosslist.get(i).getBankChange();
		}

		System.out.println("List of current user bets: " + betrepository.findByUsers(curruser));
		model.addAttribute("alldeposits", alldeposits);
		model.addAttribute("allwins", allwins);
		model.addAttribute("bets", betrepository.findByUsers(curruser));
		return "listbets";
	}

}