package com.example.bettingtracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bettingtracker.domain.Users;
import com.example.bettingtracker.domain.UsersRepository;

@Controller
public class UsersController {

	@Autowired
	private UsersRepository urepository;

	// Returns login template on /login endpoint
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Logs user out
	@RequestMapping(value = "/logout")
	public String logout() {
		return "/login";
	}

	// Sends new User to register template
	@RequestMapping(value = "/register")
	public String RegisterForm(Model model) {
		model.addAttribute("user", new Users());

		return "register";
	}

	// Saves User object that was received from register template and hashes the
	// password. Returns register_success template
	@RequestMapping(value = "/saveuser")
	public String saveUser(Users user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		user.setRole("USER"); // Gives User "USER" role
		urepository.save(user);

		return "register_success";
	}

}
