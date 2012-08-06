package fr.simplechat.repository.mondodb;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
@Singleton
public class UserRepositoryImpl  implements UserRepository{

	@Inject
	private Datastore datastore;
	
	

	@Override
	public User createUser(User user) {
		datastore.save(user);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		Query<User>query= datastore.find(User.class, "email", email);
		return query.get();
	}

	@Override
	public void remove(ObjectId id) {
		datastore.delete(User.class, id);
		
	}

}
