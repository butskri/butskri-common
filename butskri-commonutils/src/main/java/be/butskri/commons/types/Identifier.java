package be.butskri.commons.types;

import java.io.Serializable;

public class Identifier implements Serializable {

	private static final long serialVersionUID = -4400954075797628375L;

	private Long id;

	public Identifier(Long id) {
		this.id = id;
	}

	public Identifier(String id) {
		this.id = Long.valueOf(id);
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Identifier) {
			Identifier identifier = (Identifier) obj;
			if (id.equals(identifier.id)) {
				result = true;
			}
		}
		return result;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
