package be.butskri.commons.security;

import java.util.Arrays;
import java.util.Collection;

public class SimpleUser implements User {

	private String name;
	private Collection<String> roles;
	
	public SimpleUser(String name, String... roles) {
		this.name = name;
		this.roles = Arrays.asList(roles);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isInRole(String role) {
		return roles.contains(role);
	}
}
