package fr.simplechat.repository.mondodb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.test.tools.DefaultTest;


public class TestUserRepositoryImplTest extends DefaultTest{
	
	
	@Inject
	private UserRepository userRepository;
	
	@Test
	public void testCreateRead(){
		 String email="azerty@gmail.com";
		 assertNull(userRepository.findUserByEmail(email));
		 User user=new User();
		 user.setEmail(email);
		 user.setName("azerty");
		 user.setPassword("1234");
		 user=userRepository.createUser(user);
		 assertNotNull(userRepository.findUserByEmail(email));
		 userRepository.remove(user.getId().toString());
	
		 assertNull(userRepository.findUserByEmail(email));
	}
	
	

}
