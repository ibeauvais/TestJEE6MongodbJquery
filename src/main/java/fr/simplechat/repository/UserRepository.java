package fr.simplechat.repository;

import java.util.List;

import org.bson.types.ObjectId;

import fr.simplechat.model.User;

public interface UserRepository {
	
	
	
	public User createUser(User user);
	public User findUserByEmail(String email);
	public void remove(ObjectId id);
	public List<User> findAll();

}
