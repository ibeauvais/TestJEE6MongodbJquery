package fr.simplechat.repository;

import java.util.List;

import fr.simplechat.model.User;

public interface UserRepository {
	
	
	
	 User createUser(User user);
	 User findUserByEmail(String email);
	 void remove(String id);
	 List<User> findAll();
	User findById(String id);

}
