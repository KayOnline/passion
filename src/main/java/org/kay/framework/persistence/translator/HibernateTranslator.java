package org.kay.framework.persistence.translator;

import java.util.Collections;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;

public class HibernateTranslator implements ITranslator {

	@Override
	public String translateJPQL2SQL(String jpql, EntityManager em) {
		Session session = (Session) em.getDelegate();
		SessionFactory sessionFactory = session.getSessionFactory();
		QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();
		QueryTranslator queryTranslator = ast.createQueryTranslator(jpql, jpql, Collections.EMPTY_MAP,
				(SessionFactoryImplementor) sessionFactory, null);
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		return queryTranslator.getSQLString();
	}

}
