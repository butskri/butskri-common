package be.butskri.commons.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import be.butskri.commons.context.Context;
import be.butskri.commons.exception.ButskriRuntimeException;

public class SessionLoader {

	private Context context;
	private SessionFactory sessionFactory;
	private Session session;

	SessionLoader(Context context, SessionFactory sessionFactory) {
		this.context = context;
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		if (session == null) {
			try {
				session = sessionFactory
						.openSession(new InterceptorForUpdatingModificationOrCreationData(
								context));
			} catch (HibernateException e) {
				throw new ButskriRuntimeException(e, "error.getting.session");
			}
		}

		return session;
	}

	public void closeSession() {
		try {
			if (session != null) {
				session.close();
			}
		} catch (HibernateException e) {
			throw new ButskriRuntimeException(e, "error.closing.session");
		} finally {
			session = null;
		}
	}
}
