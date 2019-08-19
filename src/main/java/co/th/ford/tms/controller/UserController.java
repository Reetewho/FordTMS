package co.th.ford.tms.controller;

//import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.DepartmentService;
//import co.th.ford.tms.aesencrypt.AESCrypt;
/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Controller
@RequestMapping("/")
public class UserController {
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	UserService uservice;

	@Autowired
	MessageSource messageSource;

	@Autowired
	RolesService rolesService;

	@Autowired
	DepartmentService departmentService;

	public boolean checkAuthorization(HttpSession session) {
		if (session.getAttribute("S_FordUser") == null) {
			return false;
		} else {
			return true;
		}
	}

	private List<String> ROLE = Arrays.asList("ADMIN", "USER");

	/*
	 * This method will list all existing User.
	 */
	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public String listUser(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);

		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<Department> ListDepartment = departmentService.findAllDepartment();
		model.addAttribute("ListDepartments", ListDepartment);

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
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		model.addAttribute("roleList", ROLE);
		if (result.hasErrors()) {
			return "user-detail";
		}

		if (!uservice.isUsernameUnique(user.getUsername())) {
			model.addAttribute("usernameErr", messageSource.getMessage("non.unique.username",
					new String[] { user.getUsername() }, Locale.getDefault()));
			return "user-detail";
		}

		uservice.saveUser(user);

		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("saveSuccess",
				messageSource.getMessage("save.success", new String[] { user.getUsername() }, Locale.getDefault()));

		return "user-list";
	}

	@RequestMapping(value = { "/userDetail/{editUsername}" }, method = RequestMethod.GET)
	public String editEmployee(HttpSession session, @PathVariable String editUsername, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		User user = uservice.findUserByusername(editUsername);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);

		String passwordn = uservice.decryptUserPassword(user.getPassword());	
		model.addAttribute("passwordn", passwordn);
		
		
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<Department> ListDepartment = departmentService.findAllDepartment();
		model.addAttribute("ListDepartments", ListDepartment);

		return "user-detail";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/userDetail/{editUsername}" }, method = RequestMethod.POST)
	public String updateEmployee(HttpSession session, @Valid User user, BindingResult result,
			@RequestParam int ListRolests, @RequestParam int ListDepartments,@RequestParam String Contactnumbers, ModelMap model,
			@PathVariable String editUsername) {
		if (!checkAuthorization(session))return "redirect:/login";
		
		
		
		
        user.setContactnumber(Contactnumbers);
		user.setRole(ListRolests);
		user.setDepartment(ListDepartments);
		
		uservice.updateUser(user);

		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
	
		return "redirect:/userList";
	}
	
	/*---------------------------------------------------------------*/
	@RequestMapping(value = { "/userReset/{editUsername}" }, method = RequestMethod.GET)
	public String resetEmployee(HttpSession session, @PathVariable String editUsername, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		
		
		User user = uservice.findUserByusername(editUsername);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);

		String passwordn = uservice.decryptUserPassword(user.getPassword());	
		model.addAttribute("passwordn", passwordn);
			

		return "user-Reset";
	}
	
	
	
	
	
	@RequestMapping(value = { "/userReset/{editUsername}" }, method = RequestMethod.POST)
	public String resetEmployee(HttpSession session,@RequestParam String Conpasswordsc,  ModelMap model,	@PathVariable String editUsername) {
		if (!checkAuthorization(session))return "redirect:/login";

		
		User usera = uservice.findUserByusername(editUsername);
		
		usera.setPassword(uservice.encryptUserPassword(Conpasswordsc));
		uservice.updateUser(usera);

		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
	
		return "redirect:/userList";
	}
	
	
	
	
	
	
	
	
	/*---------------------------------------------------------------*/

	
	@RequestMapping(value = { "/delete-{username}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username, ModelMap model) {
		uservice.deleteUserByUsername(username);

		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("saveSuccess",
				messageSource.getMessage("update.success", new String[] { username }, Locale.getDefault()));

		return "user-list";
	}

	@RequestMapping(value = { "/UserStatus/{status}-{username}-{name}" }, method = RequestMethod.GET)
	public String updatestatus(@PathVariable String username, ModelMap model) {
		User statususer = uservice.findUserByusername(username);

		if (statususer.getStatus() == 1) {
			statususer.setStatus(0);
			uservice.updateUser(statususer);
		} else {
			statususer.setStatus(1);
			uservice.updateUser(statususer);
		}

		model.addAttribute("user", statususer);
		model.addAttribute("edit", true);
		return "redirect:/userList";
	}
	/*---------------------------------------------------------------------------------------------------------------*/

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.GET)
	public String addEmployee(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		List<User> users = uservice.findAllUsers();
		model.addAttribute("users", users);
		
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);

		List<Department> ListDepartment = departmentService.findAllDepartment();
		model.addAttribute("ListDepartments", ListDepartment);


		return "adduser";
	}

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String addEmployee(HttpSession session, @RequestParam String Usernamea,@RequestParam String namesa, @RequestParam String LastNamea,@RequestParam String inputPasswordConfirm, @RequestParam String Email,
			@RequestParam String Contactnumber, @RequestParam int ListRolests, @RequestParam int ListDepartments,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		List<User> finduser = uservice.findAllUsers();
		for(User findusers : finduser){
			
			if(findusers.getUsername().equalsIgnoreCase(Usernamea)) {
				model.addAttribute("Error", "Duplicate name : " + Usernamea + " Please Try Again.");
				
				List<User> users = uservice.findAllUsers();
				model.addAttribute("users", users);
				
				List<Roles> ListRoles = rolesService.findAllRoles();
				model.addAttribute("ListRolest", ListRoles);

				List<Department> ListDepartment = departmentService.findAllDepartment();
				model.addAttribute("ListDepartments", ListDepartment);


				return "adduser";
				
				
			}
			
		}
		
				
		  User  Newuser= new User();
		  
		  DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String strDateNow = DateTime.now().toString(dtf);
			
	        System.out.println("----------> ! Test Date Times  ! <----------" + LocalDateTime.parse(strDateNow, dtf)); 

					  
		 
		  		  
		  Newuser.setUsername(Usernamea); 
		  Newuser.setPassword(uservice.encryptUserPassword(inputPasswordConfirm));
		  Newuser.setName(namesa); 
		  Newuser.setLastname(LastNamea); 
		  Newuser.setEmail(Email);
		  Newuser.setContactnumber(Contactnumber); 
		  Newuser.setRole(ListRolests);
		  Newuser.setDepartment(ListDepartments);
		  Newuser.setJoiningDate(LocalDateTime.parse(strDateNow, dtf));		  
		  Newuser.setLogoutDate(LocalDateTime.parse(strDateNow, dtf));
		 		  
		  
		  
		  
		  
		  uservice.saveUser(Newuser);
		  		 

		return "redirect:/userList";
	}

}
