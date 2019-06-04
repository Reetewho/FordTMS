package co.th.ford.tms.controller;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import co.th.ford.tms.model.User;
import co.th.ford.tms.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	
	@Autowired
	UserService uservice;
	
	@Autowired
	MessageSource messageSource;
	
	private List<String> ROLE = Arrays.asList("ADMIN", "USER");

	
	
	/*
	 * This method will list all existing User.
	 */
	@RequestMapping(value = {"/userList" }, method = RequestMethod.GET)
	public String listUser(ModelMap model) {
		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		return "user-list";
	}

	/*
	 * This method will provide the medium to add a new User.
	 */


	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/userList" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {
		
		model.addAttribute("roleList", ROLE);
		if (result.hasErrors()) {			
			return "user-detail";
		}

		if(!uservice.isUsernameUnique(user.getUsername())){			
			model.addAttribute("usernameErr", messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
			return "user-detail";
		}
		
		uservice.saveUser(user);
		
		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("saveSuccess", messageSource.getMessage("save.success", new String[]{user.getUsername()}, Locale.getDefault()));
		
		return "user-list";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
//	@RequestMapping(value = { "/userDetail/{username}" }, method = RequestMethod.GET)
//	public String newEmployee(ModelMap model) {
//		User user = new User();
//		user.setJoiningDate(LocalDate.now());
//		model.addAttribute("user", user);
//		model.addAttribute("edit", false);
//		model.addAttribute("roleList", ROLE);
//		return "user-detail";
//	}
	
	@RequestMapping(value = { "/userDetail/{editUsername}" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String editUsername, ModelMap model) {
		/*Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);*/
		User user =uservice.findUserByusername(editUsername);
		model.addAttribute("user", user);
		//model.addAttribute("roleList", ROLE);
		model.addAttribute("edit", true);
		return "user-detail";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/userDetail/{editUsername}" }, method = RequestMethod.POST)
	public String updateEmployee(HttpSession session, @Valid User user, BindingResult result,
			ModelMap model, @PathVariable String editUsername) {
		
		//model.addAttribute("roleList", ROLE);
		//if (result.hasErrors()) {
		//	return "user-detail";
		//}			
		uservice.updateUser(user);
		
		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		//model.addAttribute("saveSuccess",  messageSource.getMessage("update.success", new String[]{user.getUsername()}, Locale.getDefault()));
		return "user-list";
	}

	
	/*
	 * This method will delete an User by it's username value.
	 */
	@RequestMapping(value = { "/delete-{username}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username, ModelMap model) {
		uservice.deleteUserByUsername(username);
		
		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("saveSuccess",  messageSource.getMessage("update.success", new String[]{username}, Locale.getDefault()));
		
		return "user-list";
	}

}
