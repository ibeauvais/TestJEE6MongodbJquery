package fr.simplechat.web.admin;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.simplechat.model.User;
import fr.simplechat.services.UserService;


@Named
@RequestScoped
public class AdminController {
	
	@Inject
	private UserService userService;
	
	public List<User> getUserList(){
		return userService.findAll();
	}
	
	
	
	public boolean isHasUser(){
		return true;
	}
	
	
	

}
