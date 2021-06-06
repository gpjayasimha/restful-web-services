package com.jay.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;

	static {
		users.add(new User("Jay", 1, new Date()));
		users.add(new User("Uma", 2, new Date()));
		users.add(new User("Anu", 3, new Date()));
	}

	public List<User> retrieveAllUsers() {
		return users;
	}

	public User retrieveUser(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}

		users.add(user);
		return user;
	}

	public User deleteUser(int id) {
		Iterator<User> userIterator = users.iterator();
		while (userIterator.hasNext()) {
			User user = userIterator.next();
			if (user.getId() == id) {
				userIterator.remove();
				return user;
			}

		}

		return null;
	}

}
