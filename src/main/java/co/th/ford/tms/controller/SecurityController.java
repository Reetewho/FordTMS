package co.th.ford.tms.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
//import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.User;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.PermissionMenu;

import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.LoadService;
import co.th.ford.tms.service.PermissionMenuService;

@Controller
@RequestMapping("/")
public class SecurityController {

	@Autowired
	LoadService lservice;
	
	@Autowired
	UserService uservice;
	
	@Autowired
	PermissionMenuService PermissionMenuService;
	
	@Autowired
	MessageSource messageSource;
	
		
	/*
	 * This method will list all existing Carrier.
	 */
	@RequestMapping(value = {"/","/login" }, method = RequestMethod.GET)
	public String login(ModelMap model) {

		
		return "login";
	}
	
	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(HttpSession session,@RequestParam String username,@RequestParam String password, ModelMap model){
		String nextPage="login";
		if(username.trim().equals("") ) {
			
		    model.addAttribute("EmptyUsername",  messageSource.getMessage("NotEmpty.user.username", new String[]{username}, Locale.getDefault()));
		}else if(password.trim().equals("")) {
			
		    model.addAttribute("EmptyPassword",  messageSource.getMessage("NotEmpty.user.password", new String[]{password}, Locale.getDefault()));
		}else {
			
			List<User> user_l =uservice.findAllUsers();
			
			for (User user_ls : user_l) {
		 
			if(user_ls !=null && user_ls.getUsername().equals(username)) {
				
				if(user_ls !=null && uservice.decryptUserPassword(user_ls.getPassword()).equals(password)) {
				
				if(user_ls.getStatus()==0) {
					
					model.addAttribute("InActive",  messageSource.getMessage("InActive.user.username", new String[]{username}, Locale.getDefault()));
					
			}else {
				User user =uservice.findUserByusername(username);
				int idroless = user.getRole();
				List<PermissionMenu> permissionMenu = PermissionMenuService.getPermissionMenu(idroless);	
				
				
					session.setAttribute("P_FordUser", (ArrayList<PermissionMenu>)permissionMenu);			 
										 
					if(user.getRole()==1 || user.getRole()==2 ) {
					session.setAttribute("S_FordUser", user);	
					
					LocalDate todaydate = LocalDate.now();	
					String endDate=todaydate.withDayOfMonth(todaydate.dayOfMonth().getMaximumValue()).toString();
					String startDate=todaydate.withDayOfMonth(1).toString();
					nextPage="redirect:/calendar/"+startDate+"/"+endDate;
					
					}else if(user.getRole()==3) {
						session.setAttribute("S_FordUser", user);		

						List<Load> Loadlistd = lservice.findLoadByusername(username);
						model.addAttribute("Loadlistd", Loadlistd);
																			
						
						nextPage="load-list-drivers";
					}
					
				}
				}else {
					model.addAttribute("loginFail",  messageSource.getMessage("Invalid.user.username", new String[]{password}, Locale.getDefault()));
				}
			}else {
				model.addAttribute("loginFail",  messageSource.getMessage("Invalid.user.username", new String[]{username}, Locale.getDefault()));
			}
		}
			
		}	
		return nextPage;
	}
	
	@RequestMapping(value = { "/logout/" }, method = RequestMethod.GET)		
		public String logout(HttpSession session ) {
			
			User dataUser = (User)session.getAttribute("S_FordUser");
						
			DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String strDateNow = DateTime.now().toString(dtf);
			
	        System.out.println("----------> ! Test Date Times  ! <----------" + LocalDateTime.parse(strDateNow, dtf)); 

			
				dataUser.setLogoutDate(LocalDateTime.parse(strDateNow, dtf));
				uservice.updateUser(dataUser);
				

							
	    session.invalidate();
	    return "redirect:/login";
	}
}
