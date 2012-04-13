package be.butskri.commons.hibernate;

import java.io.Serializable;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import be.butskri.commons.context.Context;

class InterceptorForUpdatingModificationOrCreationData extends EmptyInterceptor {

	private static final long serialVersionUID = -6138875784155252620L;

	private static final Integer INITIAL_VERSION = new Integer(1);
	private static final String VERSION = "version";
	private static final String TIME_CREATED = "timeCreated";
	private static final String TIME_MODIFIED = "timeModified";
	private static final String USER_MODIFIED = "userModified";
	private static final String USER_CREATED = "userCreated";
	private Context context;

	InterceptorForUpdatingModificationOrCreationData(Context context) {
		this.context = context;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {

		replaceProperty(propertyNames, currentState, USER_MODIFIED, context
				.getUserName());
		replaceProperty(propertyNames, currentState, TIME_MODIFIED, context
				.getSystemTime());

		return true;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {

		replaceProperty(propertyNames, state, VERSION, INITIAL_VERSION);
		replaceProperty(propertyNames, state, TIME_CREATED, context
				.getSystemTime());
		replaceProperty(propertyNames, state, USER_CREATED, context
				.getUserName());

		return true;
	}

	private int findIndex(String string, String[] strings) {
		return ArrayUtils.indexOf(strings, string);
	}

	private void replaceProperty(String[] propertyNames, Object[] state,
			String propertyName, Object propertyValue) {
		int userModifiedIndex = findIndex(propertyName, propertyNames);
		state[userModifiedIndex] = propertyValue;
	}
}
