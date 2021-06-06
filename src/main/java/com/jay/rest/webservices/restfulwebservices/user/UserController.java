package com.jay.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	UserDaoService service;

	@GetMapping("/users")
	public List<User> findAllUsers() {

		return service.retrieveAllUsers();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> findUser(@PathVariable int id) {
		User user = service.retrieveUser(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id-" + id);
		}
		
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		service.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void removeUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
