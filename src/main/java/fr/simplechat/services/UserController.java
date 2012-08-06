package fr.simplechat.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.model.User;
import fr.simplechat.services.exception.ServiceException;



@Named
@RequestScoped
public class UserController {

	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	
	private User user=new User();
	
	@Inject
	private UserService productService;
	
	
	
	public String doSave(){
		log.debug("doSave {}",user);
		try {
			productService.createUser(user);
			return "confirmationUser.jsf";
		} catch (ServiceException e) {
			return "signin.xhtml";
		}
		
		
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
}
