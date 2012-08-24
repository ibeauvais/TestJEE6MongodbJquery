package fr.simplechat.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.simplechat.model.User;
import fr.simplechat.repository.UserRepository;


@Path("/user")
@Singleton
public class UserResources {
	
	
	@Inject
	private UserRepository userRepository;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		User user= userRepository.findById(id);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAll() {
		List<User> users= userRepository.findAll();

		return users;
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") String id) {
		userRepository.remove(id);
	}

}
