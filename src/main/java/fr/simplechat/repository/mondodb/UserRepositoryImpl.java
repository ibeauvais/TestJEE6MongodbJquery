package fr.simplechat.repository.mondodb;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.UpdateOperations;
import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class UserRepositoryImpl  implements UserRepository{

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

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
	public void remove(String id) {
		datastore.delete(User.class, new ObjectId(id));
		
	}

	@Override
	public List<User> findAll() {
		Query<User> query= datastore.find(User.class);
		return query.asList();
	}

	@Override
	public User findById(String id) {
		return datastore.get(User.class, new ObjectId(id));
	}

    @Override
    public void update( User user) {
        log.debug(">>Update of user with id{} data={}",user.getId(),user);

        Query<User> updateQuery = datastore.createQuery(User.class).field(Mapper.ID_KEY).equal(user.getId());
        UpdateOperations<User> up=datastore.createUpdateOperations(User.class)
                .set("email",user.getEmail()).set("name",user.getName());

      datastore.update(updateQuery,up);

    }

}
