package mta.ltnc.BookStore.managers;

import mta.ltnc.BookStore.entity.Role;
import mta.ltnc.BookStore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Generated by Spring Data Generator on 22/06/2019
*/
@Component
public class RoleManager {

	private RoleRepository roleRepository;

	@Autowired
	public RoleManager(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

}