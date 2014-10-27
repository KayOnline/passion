package org.kay.framework.persistence.translator;

import javax.persistence.EntityManager;

public interface ITranslator {
	public abstract String translateJPQL2SQL(String jpql, EntityManager em);
}
