package co.th.ford.tms.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.controller.UserController;
import co.th.ford.tms.dao.UserDao;
import co.th.ford.tms.model.User;

import co.th.ford.tms.model.User;

import co.th.ford.tms.aesencrypt.AESCrypt;

/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	

	public void saveUser(User user) {
		dao.saveUser(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User u) {
		User entity = dao.findByUsername(u.getUsername());
		if(entity!=null){
	        System.out.println("----------> ! Test Data In User Service  ! <----------" + entity); 

			entity.setName(u.getName());
			entity.setLastname(u.getLastname());
			entity.setEmail(u.getEmail());
			entity.setJoiningDate(u.getJoiningDate());
			entity.setDepartment(u.getDepartment());
			entity.setPassword(u.getPassword());
			entity.setContactnumber(u.getContactnumber());
			entity.setRole(u.getRole());
			entity.setLogoutDate(u.getLogoutDate());
			entity.setStatus(u.getStatus());
		}
	}

	public void deleteUserByUsername(String username) {
		dao.deleteEmployeeByUsername(username);
	}
	
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
	
	public List<User> findByRole(int role) {
		return dao.findByRole(role);
	}

	public User findUserByusername(String username) {
		return dao.findByUsername(username);
	}

	public boolean isUsernameUnique(String username) {
		User user = findUserByusername(username);
		return ( user == null);
	}
	
	public String encryptUserPassword(String password) {
		
		String enPass = "enCryptFail";
		try {
			enPass = AESCrypt.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enPass;
		
	}
	
public String decryptUserPassword(String password) {
		
		String dePass = "enCryptFail";
		try {
			dePass = AESCrypt.decrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dePass;
		
	}
}
