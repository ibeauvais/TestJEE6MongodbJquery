package fr.simplechat.repository.cassandra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import org.junit.Test;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import fr.simplechat.tootls.CassandraTest;

public class TestUserRepositoryCassandraImplTest extends CassandraTest{
	
	
	
	 
	 
	 @Inject
	 private UserRepository userRepository;
	 
	 @Test
	 public void testCreateUserAndFindUserByEmail(){
		 String email="azerty@gmail.com";
		 assertNull(userRepository.findUserByEmail(email));
		 User user=new User();
		 user.setEmail("azerty@gmail.com");
		 user.setName("azerty");
		 user.setPassword("1234");
		 userRepository.createUser(user);
		 
		 User userResult=userRepository.findUserByEmail(email);
		 assertNotNull(userResult);
		 assertEquals("azerty",userResult.getName());
		 assertEquals("1234",userResult.getPassword());
	 }


}
