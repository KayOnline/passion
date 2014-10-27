package org.kay.framework.test;

import org.junit.runner.RunWith;
import org.kay.framework.persistence.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		//
		//"classpath*:dispatcher-servlet.xml", 
		"classpath*:dataSource-context.xml", 
		"classpath*:persistence-context.xml",
		"classpath*:security-context.xml",
		"classpath*:application-context.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
public abstract class BaseSpringTestSupport {

	@Autowired
	protected BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
