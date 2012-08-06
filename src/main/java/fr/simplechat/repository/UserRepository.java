package fr.simplechat.repository;

import fr.simplechat.model.User;

public interface UserRepository {
	
	
	
	public User createUser(User user);
	public User findUserByEmail(String email);

}
