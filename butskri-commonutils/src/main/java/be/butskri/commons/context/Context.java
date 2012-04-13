package be.butskri.commons.context;

import java.util.Date;

import be.butskri.commons.security.User;

public class Context {

	private static final String DEFAULT_USER_NAME = "nobody";
	private User user;
	private Date systemDate;
	private Date systemTime;

	Context(User user, Date systemDate, Date systemTime) {
		this.user = user;
		this.systemDate = systemDate;
		this.systemTime = systemTime;
	}

	public User getUser() {
		return user;
	}

	public Date getSystemDate() {
		return systemDate;
	}

	public Date getSystemTime() {
		return systemTime;
	}
	
	public String getUserName() {
		String result = DEFAULT_USER_NAME;
		if (user != null) {
			result = user.getName();
		}
		return result;
	}
}
