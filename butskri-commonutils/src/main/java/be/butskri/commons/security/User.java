package be.butskri.commons.security;

public interface User {
	
	String getName();
	
	boolean isInRole(String role);

}
