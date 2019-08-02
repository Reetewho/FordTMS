package co.th.ford.tms.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
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
import co.th.ford.tms.model.PermissionMenu;

import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.PermissionMenuService;

@Controller
@RequestMapping("/")
public class SecurityController {

	
	
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

		//List<Employee> employees = service.findAllEmployees();
		//model.addAttribute("employees", employees);
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
			
			User user =uservice.findUserByusername(username);
			int idroless = user.getRole();
			List<PermissionMenu> permissionMenu = PermissionMenuService.getPermissionMenu(idroless);	
			 
			if(user !=null && uservice.decryptUserPassword(user.getPassword()).equals(password)) {
				
				if(user.getStatus()==0) {
					
					model.addAttribute("InActive",  messageSource.getMessage("InActive.user.username", new String[]{username}, Locale.getDefault()));
					
			}else {
				
					session.setAttribute("P_FordUser", (ArrayList<PermissionMenu>)permissionMenu);			 
					//ArrayList<PermissionMenu> SpermissionMenu = (ArrayList<PermissionMenu>)session.getAttribute("P_FordUser");
					//System.out.println("T#E#S#T#Q#U#E#R#Y"+SpermissionMenu);

					 
					if(user.getRole()==1) {
					session.setAttribute("S_FordUser", user);				
					LocalDate todaydate = LocalDate.now();	
				String endDate=todaydate.withDayOfMonth(todaydate.dayOfMonth().getMaximumValue()).toString();
					String startDate=todaydate.withDayOfMonth(1).toString();
					nextPage="redirect:/calendar/"+startDate+"/"+endDate;
					
					}else if(user.getRole()==2) {
										
						session.setAttribute("S_FordUser", user);				
						nextPage="load-list-drivers";
					}
					
				}
			}else {
				model.addAttribute("loginFail",  messageSource.getMessage("Invalid.user.username", new String[]{username}, Locale.getDefault()));
			}
		}
			
		return nextPage;
	}
	
	@RequestMapping(value = { "/logout/" }, method = RequestMethod.GET)		
		public String logout(HttpSession session ) {
			
			User dataUser = (User)session.getAttribute("S_FordUser");
						
			LocalDate datesession = LocalDate.now();
			
				dataUser.setLogoutDate(datesession);

				uservice.updateUser(dataUser);
				

							
	    session.invalidate();
	    return "redirect:/login";
	}
}
