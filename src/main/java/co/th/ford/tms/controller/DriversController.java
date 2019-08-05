/*
 * package co.th.ford.tms.controller;
 * 
 * //import java.util.ArrayList; import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.MessageSource; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.ModelMap; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * 
 * import co.th.ford.tms.model.User; import
 * co.th.ford.tms.service.DepartmentService; import
 * co.th.ford.tms.service.RolesService; import
 * co.th.ford.tms.service.UserService;
 * 
 * 
 * 
 * @Controller
 * 
 * @RequestMapping("/") public class DriversController {
 * 
 * 
 * @Autowired UserService uservice;
 * 
 * @Autowired MessageSource messageSource;
 * 
 * @Autowired RolesService rolesService;
 * 
 * @Autowired DepartmentService departmentService;
 * 
 * 
 * @RequestMapping(value = {"/load-list"}, method = RequestMethod.GET) public
 * String driversCheck(ModelMap model, HttpServletRequest request) {
 * 
 * List<User> users = uservice.findAllUsers(); model.addAttribute("users",
 * users);
 * 
 * System.out.println("---------> Start Request[POST] <--------- " +
 * "get Result SelectMultipleCheck : " + users +
 * "---------> End Request[POST] <---------");
 * 
 * 
 * return "drivers"; }
 * 
 * @RequestMapping(value = {"/drivers"}, method = RequestMethod.POST) public
 * String driversCheck(@RequestParam("console-select-rows") String
 * getSelectItemData, ModelMap model, HttpServletRequest request) {
 * System.out.println("---------> Start Request[POST] <--------- " +
 * "get Result SelectMultipleCheck : " + getSelectItemData +
 * "---------> End Request[POST] <---------");
 * 
 * 
 * 
 * String[] arrOfSelectItem = getSelectItemData.split(","); int nItem = 1; for
 * (String arrItem : arrOfSelectItem) {
 * 
 * System.out.println("Result " + nItem + " | Item : " + arrItem); nItem++; }
 * 
 * model.addAttribute("lists", arrOfSelectItem); return "/load-list"; }
 * 
 * }
 */