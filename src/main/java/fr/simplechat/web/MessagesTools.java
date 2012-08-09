package fr.simplechat.web;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesTools {

	
	
	
	public static void addErrorForComponent(String componentId,String message) {
		FacesContext context = FacesContext.getCurrentInstance();
	    context.addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR,message, null));
	}
	
	
	
	public static void addGlobalError(String message) {
		 addErrorForComponent("inputForm",message);
		
	}



	public static String getMessage(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Locale locale = facesContext.getViewRoot().getLocale();
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    ResourceBundle bundle = ResourceBundle.getBundle("application", locale, classLoader);
	    return bundle.getString(key);
	}

}
