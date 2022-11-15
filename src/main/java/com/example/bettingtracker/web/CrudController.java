package com.example.bettingtracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bettingtracker.domain.BetData;
import com.example.bettingtracker.domain.BetDataRepository;
import com.example.bettingtracker.domain.Bookmaker;
import com.example.bettingtracker.domain.BookmakerRepository;
import com.example.bettingtracker.domain.Deposit;
import com.example.bettingtracker.domain.DepositRepository;
import com.example.bettingtracker.domain.SportsType;
import com.example.bettingtracker.domain.SportsTypeRepository;
import com.example.bettingtracker.domain.Users;
import com.example.bettingtracker.domain.UsersRepository;

@Controller
public class CrudController {

	@Autowired
	private BetDataRepository betrepository;

	@Autowired
	private SportsTypeRepository srepository;

	@Autowired
	private BookmakerRepository bmrepository;

	@Autowired
	private DepositRepository deporepository;

	@Autowired
	private UsersRepository urepository;

	// Deletes BetData by ID
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long betDataID, Model model) {
		betrepository.deleteById(betDataID);
		return "redirect:../";
	}

	// Sends a new Bookmaker object to addbookie template with ID (ID is sent to
	// saveBookie method)
	@RequestMapping(value = "/addbookie/{id}")
	public String addBookie(@PathVariable("id") Long BetDataID, Model model) {

		model.addAttribute("bookmaker", new Bookmaker());
		return "addbookie";
	}

	// Sends a new Bookmaker object to addbookie template
	@RequestMapping(value = "/addbookie")
	public String addBookie(Model model) {

		model.addAttribute("bookmaker", new Bookmaker());
		return "addbookie";
	}

	// Saves Bookmaker object that was received from addbookie template
	@RequestMapping(value = "/savebookie", method = RequestMethod.POST)
	public String saveBookie(Bookmaker bookmaker) {
		bmrepository.save(bookmaker);
		return "redirect:/add";
	}

	// Saves the Bookmaker object that was received from addbookie template (ID is
	// needed to redirect back to the right edited BetData)
	@RequestMapping(value = "/savebookie/{id}", method = RequestMethod.POST)
	public String saveBookie(@PathVariable("id") Long BetDataID, Bookmaker bookmaker) {
		bmrepository.save(bookmaker);
		return "redirect:/edit/" + BetDataID;
	}

	// Sends a new SportsType object to addsport template
	@RequestMapping(value = "/addsport")
	public String addSport(Model model) {

		model.addAttribute("sportstype", new SportsType());
		return "addsport";
	}

	// Sends a new SportsType object to addsport template with ID (ID is sent to
	// saveSport method)
	@RequestMapping(value = "/addsport/{id}")
	public String addSport(@PathVariable("id") Long BetDataID, Model model) {

		model.addAttribute("sportstype", new SportsType());
		return "addsport";
	}

	// Saves the SportsType object that was received from addsport template
	@RequestMapping(value = "/savesport", method = RequestMethod.POST)
	public String saveSport(SportsType sportstype) {
		srepository.save(sportstype);
		return "redirect:/add";
	}

	// Saves the SportsType object that was received from addbookie template (ID is
	// needed to redirect back to the right edited BetData)
	@RequestMapping(value = "/savesport/{id}", method = RequestMethod.POST)
	public String saveSport(@PathVariable("id") Long BetDataID, SportsType sportstype) {
		srepository.save(sportstype);
		return "redirect:/edit/" + BetDataID;
	}

	// Sends the correct BetData that wants to be edited to editbet template (Found
	// by ID)
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long betDataID, Model model) {

		model.addAttribute("betdata", betrepository.findById(betDataID));
		model.addAttribute("sportslist", srepository.findAll());
		model.addAttribute("bookerlist", bmrepository.findAll());

		return "editbet";

	}

	// Sends new Deposit object to adddepo template
	@RequestMapping(value = "/adddepo")
	public String addDepo(Model model) {

		model.addAttribute("deposit", new Deposit());
		return "adddepo";
	}

	// Saves the Deposit object that was received from adddepo template
	@RequestMapping(value = "/savedepo")
	public String saveDepo(Deposit deposit) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users curruser = urepository.findByUsername(username);
		deposit.setUsers(curruser);

		deporepository.save(deposit);
		return "redirect:../";
	}

	// Sends new BetData object and all SportTypes and Bookmakers to addbet template
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBet(Model model) {

		model.addAttribute("betdata", new BetData());
		model.addAttribute("sportslist", srepository.findAll());
		model.addAttribute("bookerlist", bmrepository.findAll());

		return "addbet";
	}

	// Saves the BetData object from addbet to repository and adds current userID
	// foreign key
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBet(BetData betdata) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users curruser = urepository.findByUsername(username); // Finds current user

		betdata.setUsers(curruser); // Gives BetData object the current user to set foreign key
		betrepository.save(betdata);

		return "redirect:/";
	}

}
