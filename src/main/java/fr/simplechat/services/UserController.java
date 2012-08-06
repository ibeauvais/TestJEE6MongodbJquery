package fr.simplechat.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.model.User;



@Named
@RequestScoped
public class UserController {

	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	
	private User user=new User();
	
	@Inject
	private UserService productService;
	
	
	
	public String doSave(){
		log.debug("doSave {}",user);
		productService.createUser(user);
		
		return "confirmationUser.jsf";
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
}
