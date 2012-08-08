package fr.simplechat.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.model.User;
import fr.simplechat.services.UserService;
import fr.simplechat.services.exception.ServiceException;



@Named
@RequestScoped
public class UserController {

	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	
	private User user=new User();
	private String verifPassword;
	
	@Inject
	private UserService productService;
	
	
	
	public String doSave(){
		log.debug("doSave {}",user);
		if(!isValidPassword())
			return null;
		
		try {
			
			productService.createUser(user);
			return "confirmationUser.jsf";
		} catch (ServiceException e) {
			addGlobalError(e.getMessage());
			return null;
		}
		
		
	}



	private boolean isValidPassword() {
		if(verifPassword!=null && verifPassword.equals(user.getPassword()))
			return true;
		
		addErrorForComponent("inputForm:verifPassword","Différent de password");
		return false;
		
	}



	private void addGlobalError(String message) {
		 addErrorForComponent("inputForm",message);
		
	}



	private void addErrorForComponent(String componentId,String message) {
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR,message, null));
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getVerifPassword() {
		return verifPassword;
	}



	public void setVerifPassword(String verifPassword) {
		this.verifPassword = verifPassword;
	}
}
