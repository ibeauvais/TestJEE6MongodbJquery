package fr.simplechat.repository.cassandra;

import javax.inject.Inject;

import me.prettyprint.hom.EntityManagerImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;


public class UserRepositoryCassandraImpl implements UserRepository{
	
	private static final Logger log=LoggerFactory.getLogger(UserRepositoryCassandraImpl.class);

	
	
	
	
	@Inject
	private EntityManagerImpl entityManager;
	
	
	@Override
	public User createUser(User user) {
		return entityManager.persist(user);
		
	}

	@Override
	public User findUserByEmail(String email) {
		return entityManager.find(User.class, email);
	}

}
