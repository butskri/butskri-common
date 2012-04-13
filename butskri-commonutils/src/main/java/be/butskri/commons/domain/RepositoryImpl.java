package be.butskri.commons.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import be.butskri.commons.types.Identifier;

public abstract class RepositoryImpl<T extends DomainObject> implements Repository<T> {

	private Class<T> clazz;

	protected RepositoryImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("select obj from " + clazz.getName() + " obj").getResultList();
	}

	@Override
	public T lookUpById(Identifier id) {
		return getEntityManager().find(clazz, id.getId());
	}

	@Override
	public T persist(T domainObject) {
		domainObject = getEntityManager().merge(domainObject);
		return domainObject;
	}

	@Override
	public void remove(T domainObject) {
		getEntityManager().remove(domainObject);
	}

	protected Session getSession() {
		HibernateEntityManager hibernateEntityManager = (HibernateEntityManager) getEntityManager();
		return hibernateEntityManager.getSession();
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(clazz);
	}

	protected abstract EntityManager getEntityManager();
}
