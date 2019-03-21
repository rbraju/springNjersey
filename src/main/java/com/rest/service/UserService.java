package com.rest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.rest.entity.User;

@Component
@Path("/user")
@SuppressWarnings("unchecked")
public class UserService {

	/**
	 * List all users
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/profile/all")
	public List<User> getUserProfiles() throws Exception {
		List<User> list = new ArrayList<>();
		for (User user : getUsers().values()) {
			list.add(user);
		}
		return list;
	}

	/**
	 * Retrieve user profile
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/profile")
	public Response getProfile(@QueryParam("id") int userid) throws Exception {
		User user = getUsers().get(userid);
		Object entity = (user == null) ? "Invalid userid" : user;
		int status = (user == null) ? 400 : 200;
		return Response.ok(entity).status(status).type(MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/address")
	public Response getAddress(@QueryParam("id") int userid) throws Exception {
		User user = getUsers().get(userid);
		Object entity = (user == null) ? "Invalid userid" : user.getAddress();
		int status = (user == null) ? 400 : 200;
		return Response.ok(entity).status(status).type(MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/profile")
	public Response addUser(User user) throws Exception {
		ObjectOutputStream oos = null;
		try {
			Map<Integer, User> users = getUsers();
			users.put(user.getId(), user);

			File file = ResourceUtils.getFile("classpath:db/users.txt");
			file.getAbsolutePath();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(users);
		} finally {
			if (oos != null)
				oos.close();
		}
		return Response.ok(user).type(MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/profile")
	public Response updateUser(User user) throws Exception {
		ObjectOutputStream oos = null;
		try {
			Map<Integer, User> users = getUsers();
			users.put(user.getId(), user);

			File file = ResourceUtils.getFile("classpath:db/users.txt");
			file.getAbsolutePath();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(users);
		} finally {
			if (oos != null)
				oos.close();
		}
		return Response.ok(user).type(MediaType.APPLICATION_JSON).build();
	}

	/**
	 * Get all users from DB (users.txt)
	 * 
	 * @return
	 * @throws Exception
	 */
	private Map<Integer, User> getUsers() throws Exception {
		File file = ResourceUtils.getFile("classpath:db/users.txt");
		System.out.println(file.getAbsolutePath());
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Map<Integer, User> users = (Map<Integer, User>) ois.readObject();
		ois.close();
		return users;
	}
}