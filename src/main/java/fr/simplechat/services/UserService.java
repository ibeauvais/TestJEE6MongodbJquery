package fr.simplechat.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.common.base.Preconditions;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;



@Stateless
public class UserService {
	
	@Inject
	private UserRepository userRepository;
	
	public void createUser(User user){
		Preconditions.checkNotNull(user,"User is null");
		Preconditions.checkNotNull(user.getEmail(),"Email is null");
		Preconditions.checkNotNull(user.getPassword(),"Password is null");
		
		
		User userFound=userRepository.findUserByEmail(user.getEmail());
		
		if(userFound!=null)
			throw new IllegalArgumentException("Email déja présent");
		userRepository.createUser(user);
		
	}

}
