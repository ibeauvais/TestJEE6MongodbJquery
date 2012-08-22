package fr.simplechat.services;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.services.exception.ServiceException;
import fr.simplechat.test.tools.DefaultTest;

public class TestUserServiceTest extends DefaultTest {
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void init(){
		 user =new User();
		 user.setName("azerty");
		 user.setPassword("1234");
		 user.setEmail("azerty@gmail.com");
		 
		 User userFound=userRepository.findUserByEmail(user.getEmail());
		 if(userFound!=null)
			 userRepository.remove(userFound.getId().toStringMongod());
	}
	
	@Test(expected=ServiceException.class)
	public void testCreateUserTwice() throws ServiceException{
		
	
		userService.createUser(user);
		user.setId(null);
		userService.createUser(user);
	}
	
	@Test
	public void testCreateUser() throws ServiceException{
		
	
		userService.createUser(user);
		assertUserExistAndEquals(user);
	}

	private void assertUserExistAndEquals(User user) {
		 User userFound=userRepository.findUserByEmail(user.getEmail());
		 
		 Assert.assertEquals(user,userFound);
		
	}


}
