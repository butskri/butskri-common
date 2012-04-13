package be.butskri.commons.context;

import java.util.Date;

import be.butskri.commons.security.User;
import be.butskri.commons.util.DateUtils;

public class ContextFactory {

	public Context createContext(User user) {
		Date systemTime = DateUtils.currentTime();
		Date systemDate = DateUtils.clearTime(systemTime);
		return new Context(user, systemDate, systemTime);
	}
}
