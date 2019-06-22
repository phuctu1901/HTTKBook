package mta.ltnc.BookStore.managers;

import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Component
public class UserManager {

	private UserRepository userRepository;

	@Autowired
	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}