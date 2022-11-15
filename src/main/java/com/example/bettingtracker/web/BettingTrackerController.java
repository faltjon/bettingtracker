package com.example.bettingtracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class BettingTrackerController {

	@Autowired
	private BetDataRepository betrepository;

	@Autowired
	private SportsTypeRepository srepository;

	@Autowired
	private DepositRepository depoRepository;

	@Autowired
	private BookmakerRepository bmRepository;

	@Autowired
	private UsersRepository urepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listQuestions(Model model) {

		// Finds current user to list their bets
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println("Current username is: " + username);
		Users curruser = urepository.findByUsername(username);
		System.out.println("Current user object is: " + curruser);
		Long currentUserID = curruser.getUserID();
		System.out.println("Current user ID is: " + currentUserID);

		List<Deposit> deposits = depoRepository.findByUsers(curruser);

		Double alldeposits = 0.0;
		for (int i = 0; i < deposits.size(); i++) {
			alldeposits += deposits.get(i).getAmount();
		}
		System.out.println("All deposits amount= " + alldeposits);

		System.out.println("List of current user bets: " + betrepository.findByUsers(curruser));
		model.addAttribute("alldeposits", alldeposits);
		model.addAttribute("bets", betrepository.findByUsers(curruser));
		return "listbets";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "/moro";
	}

	@RequestMapping(value = "/register")
	public String RegisterForm(Model model) {
		model.addAttribute("user", new Users());

		return "register";
	}

	@RequestMapping(value = "/saveuser")
	public String saveUser(Users user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		System.out.println("Saved user is: " + user);
		user.setRole("USER");
		urepository.save(user);
		System.out.println("user that was saved to user repo: " + user);
		Users saveduser = urepository.findByUsername("moro");
		System.out.println("User found from repo with name 'moro': " + saveduser);

		return "register_success";
	}

	@RequestMapping(value = "/adddepo")
	public String addDepo(Model model) {

		model.addAttribute("deposit", new Deposit());

		return "adddepo";
	}

	@RequestMapping(value = "/savedepo")
	public String saveDepo(Deposit deposit) {

		System.out.println("Received deposit object: " + deposit);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println("Current username is: " + username);
		Users curruser = urepository.findByUsername(username);
		deposit.setUsers(curruser);
		System.out.println("Saved deposit object with user: " + deposit);

		depoRepository.save(deposit);
		return "redirect:../";
	}

	// Add new BetData
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBet(Model model) {

		model.addAttribute("betdata", new BetData());
		model.addAttribute("sportslist", srepository.findAll());
		model.addAttribute("bookerlist", bmRepository.findAll());

		return "addbet";
	}

	// Saves BetData object to repository and adds current userID foreign key
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBet(BetData betdata) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users curruser = urepository.findByUsername(username); // Finds current user

		System.out.println("Saved BetData object before setting user: " + betdata);
		betdata.setUsers(curruser); // Gives BetData object the current user to set foreign key

		System.out.println("Saved BetData object after setting user: " + betdata);
		betrepository.save(betdata);

		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long betDataID, Model model) {
		betrepository.deleteById(betDataID);
		return "redirect:../";
	}

	@RequestMapping(value = "/addbookie/{id}")
	public String addBookie(@PathVariable("id") Long BetDataID, Model model) {

		model.addAttribute("bookmaker", new Bookmaker());

		return "addbookie";
	}

	@RequestMapping(value = "/addbookie")
	public String addBookie(Model model) {

		model.addAttribute("bookmaker", new Bookmaker());
		return "addbookie";
	}

	@RequestMapping(value = "/savebookie", method = RequestMethod.POST)
	public String saveBookie(Bookmaker bookmaker) {
		bmRepository.save(bookmaker);
		return "redirect:/add";
	}

	@RequestMapping(value = "/savebookie/{id}", method = RequestMethod.POST)
	public String saveBookie(@PathVariable("id") Long BetDataID, Bookmaker bookmaker) {
		bmRepository.save(bookmaker);
		return "redirect:/edit/" + BetDataID;
	}

	@RequestMapping(value = "/addsport")
	public String addSport(Model model) {

		model.addAttribute("sportstype", new SportsType());
		return "addsport";
	}

	@RequestMapping(value = "/addsport/{id}")
	public String addSport(@PathVariable("id") Long BetDataID, Model model) {

		model.addAttribute("sportstype", new SportsType());

		return "addsport";
	}

	@RequestMapping(value = "/savesport", method = RequestMethod.POST)
	public String saveSport(SportsType sportstype) {
		srepository.save(sportstype);
		return "redirect:/add";
	}

	@RequestMapping(value = "/savesport/{id}", method = RequestMethod.POST)
	public String saveSport(@PathVariable("id") Long BetDataID, SportsType sportstype) {
		srepository.save(sportstype);
		return "redirect:/edit/" + BetDataID;
	}

	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long betDataID, Model model) {

		model.addAttribute("betdata", betrepository.findById(betDataID));

		model.addAttribute("sportslist", srepository.findAll());
		model.addAttribute("bookerlist", bmRepository.findAll());

		return "editbet";

	}

}