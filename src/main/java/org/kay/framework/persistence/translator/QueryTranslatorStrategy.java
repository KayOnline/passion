package org.kay.framework.persistence.translator;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.kay.framework.persistence.exception.JPAException;

public abstract class QueryTranslatorStrategy {

	private static final Logger logger = Logger.getLogger(QueryTranslatorStrategy.class);

	public static String translate(String jpql, EntityManager em) {
		if (em.getDelegate() instanceof Session) {
			ITranslator translator = new HibernateTranslator();
			return translator.translateJPQL2SQL(jpql, em);
		} else {
			logger.error("Translate unsupported!");
			throw new JPAException("Translate unsupported!");
		}
	}
}
