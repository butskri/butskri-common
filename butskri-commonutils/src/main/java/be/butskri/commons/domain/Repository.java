package be.butskri.commons.domain;

import java.util.List;

import be.butskri.commons.types.Identifier;

public interface Repository<T> {

	T lookUpById(Identifier id);

	List<T> findAll();

	T persist(T domainObject);

	void remove(T domainObject);

}
