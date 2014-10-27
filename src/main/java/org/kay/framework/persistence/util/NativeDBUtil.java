package org.kay.framework.persistence.util;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import org.kay.framework.persistence.dao.BaseDao;

public class NativeDBUtil {

	private static final Logger logger = Logger.getLogger(NativeDBUtil.class);

	private static NativeDBUtil me;

	private NativeDBUtil() {
	}

	public static NativeDBUtil getInstance() {
		if (me == null) {
			me = new NativeDBUtil();
		}
		return me;
	}

	public long genSequenceNo(String sequenceName) {

		BaseDao baseDao = BeanLocator.getBean("baseDao", BaseDao.class);
		String sqlString = " SELECT " + sequenceName.toUpperCase() + ".NEXTVAL FROM DUAL ";
		List<Object> resultList = baseDao.findByNativeQuery(sqlString);
		Long result = 0L;
		if (isFirstElementExist(resultList)) {
			result = ((BigDecimal) resultList.get(0)).longValue();
		} else {
			result = -1L;
			logger.error(" Fail to get sequence no '" + sequenceName + "'");
		}
		return result;
	}

	private <T> boolean isFirstElementExist(Collection<T> resultList) {
		return resultList != null && resultList.size() > 0 && resultList.toArray()[0] != null;
	}

	public Timestamp getNativeTimestamp() {
		// TODO
		return new Timestamp(System.currentTimeMillis());
	}

}
