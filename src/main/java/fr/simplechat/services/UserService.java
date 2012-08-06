package fr.simplechat.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Preconditions;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.services.exception.ServiceException;


@Singleton
public class UserService {
	
	
	
	@Inject
	private UserRepository userRepository;
	
	public void createUser(User user) throws ServiceException{
		Preconditions.checkNotNull(user,"User is null");
		Preconditions.checkNotNull(user.getEmail(),"Email is null");
		Preconditions.checkNotNull(user.getPassword(),"Password is null");
		
		
		User userFound=userRepository.findUserByEmail(user.getEmail());
		if(userFound!=null)
			throw new ServiceException("Adresse email déja utilisée");
		
		userRepository.createUser(user);
		
	}

}
