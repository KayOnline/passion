package org.kay.framework.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.exception.JPAException;
import org.kay.framework.persistence.model.PageInfo;
import org.kay.framework.persistence.model.QueryParam;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.model.SortParam;
import org.kay.framework.persistence.model.SortParamList;
import org.kay.framework.persistence.translator.QueryTranslatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public EntityManager getEntityManager() throws JPAException {
		return em;
	}

	@Override
	public <T> void persist(T entity) throws JPAException {
		try {
			this.em.persist(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public <T> T merge(T entity) throws JPAException {
		try {
			return this.em.merge(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public <T> void remove(Class<T> entityClass, Object primaryKey) throws JPAException {
		try {
			T entity = this.em.find(entityClass, primaryKey);
			if (entity != null) {
				this.em.remove(entity);
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) throws JPAException {
		try {
			return this.em.find(entityClass, primaryKey);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) throws JPAException {
		try {
			return this.em.getReference(entityClass, primaryKey);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public void flush() throws JPAException {
		try {
			this.em.flush();
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public void setFlushMode(FlushModeType flushMode) throws JPAException {
		try {
			this.em.setFlushMode(flushMode);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public FlushModeType getFlushMode() throws JPAException {
		try {
			return this.em.getFlushMode();
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public void refresh(Object entity) throws JPAException {
		try {
			this.em.refresh(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public void detach(Object entity) throws JPAException {
		try {
			this.em.detach(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public boolean contains(Object entity) throws JPAException {
		boolean flag = false;
		try {
			flag = this.em.contains(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
		return flag;
	}

	@Override
	public LockModeType getLockMode(Object entity) throws JPAException {
		try {
			return this.em.getLockMode(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public int executeUpdate(String qlString, QueryParamList queryParamList) throws JPAException {
		try {
			Query query = this.em.createQuery(qlString);

			setQueryParameters(query, queryParamList);

			return query.executeUpdate();
		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	public long getTotalCount(String qlString, QueryParamList queryParamList) throws JPAException {
		try {
			Query query = this.em.createQuery(qlString);
			return Integer.valueOf(query.getSingleResult().toString()).intValue();
		} catch (NumberFormatException e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String jpqlString, QueryParamList queryParamList, PageInfo pageInfo,
			SortParamList sortParamList) throws JPAException {
		try {

			jpqlString = setSortOptions(jpqlString, sortParamList);

			Query query = this.em.createQuery(jpqlString);
			setQueryParameters(query, queryParamList);

			if (pageInfo != null) {

				// Set pagination for query
				int curPageNum = pageInfo.getCurPageNum();
				int rowOfPage = pageInfo.getRowOfPage();
				query.setFirstResult((curPageNum - 1) * rowOfPage).setMaxResults(rowOfPage);

				// Translate JPQL to SQL
				// Note: the converted sql use index parameter, make sure that
				// the parameter
				// sequence correspond to the jpql string
				String sqlString = QueryTranslatorStrategy.translate(jpqlString, this.em);
				String queryCount = " SELECT COUNT(*) FROM (" + sqlString + ") TMP_COUNT_T ";
				queryCount = this.convertIndexed2Named(queryCount, toIndexParamList(jpqlString, queryParamList));

				// Set allRowNum for PageInfo
				Query nativeQuery = this.em.createNativeQuery(queryCount);
				this.setQueryParameters(nativeQuery, queryParamList);
				int allRowNum = Integer.valueOf(nativeQuery.getSingleResult().toString());
				pageInfo.setAllRowNum(allRowNum);

			}

			return query.getResultList();

		} catch (Exception e) {
			logger.error(e, e);
			throw new JPAException(e);
		}
	}

	// Sort
	private String setSortOptions(String jpqlString, SortParamList sortParamList) {

		if (sortParamList != null && sortParamList.size() > 0) {

			if (jpqlString.toLowerCase().indexOf("order by") < 0) {
				jpqlString += " order by ";
			} else {
				jpqlString += ",";
			}

			for (SortParam sortParam : sortParamList.getSortParams()) {
				if (StringUtils.isEmpty(sortParam.getSortName())) {
					continue;
				}
				// Deal with alias
				if (!StringUtils.isEmpty(sortParam.getAlias())) {
					jpqlString += sortParam.getAlias() + ".";
				}
				jpqlString += sortParam.getSortName() + " " + sortParam.getSortType() + ",";
			}
			jpqlString = jpqlString.substring(0, jpqlString.length() - 1);
		}
		return jpqlString;
	}

	/**
	 * 将JPQL语句中命名参数依次存入List中
	 * 
	 * @param jpqlString
	 * @param queryParamList
	 * @return
	 */
	private List<String> toIndexParamList(String jpqlString, QueryParamList queryParamList) {
		List<String> indexParameters = new ArrayList<String>();
		String regex = "[']|([:][\\w]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(jpqlString);
		boolean isSurroundedBySingleQuotes = false; // 用于忽略单引号所包含的内容
		while (matcher.find()) {
			if (matcher.group().equals("'")) {
				isSurroundedBySingleQuotes = !isSurroundedBySingleQuotes;
			} else if (!isSurroundedBySingleQuotes && matcher.group().startsWith(":")) {
				indexParameters.add(matcher.group().substring(1));
			}
		}
		return indexParameters;
	}

	/**
	 * 将查询语句中的索引参数转换为命名参数
	 * 
	 * @param qlString
	 * @param indexParameters
	 * @return
	 */
	private String convertIndexed2Named(String qlString, List<String> indexParameters) {
		StringBuffer newSql = new StringBuffer();
		Pattern pattern = Pattern.compile("[']|[?]");
		Matcher matcher = pattern.matcher(qlString);
		boolean isSurroundedBySingleQuotes = false; // 用于忽略单引号所包含的内容
		int index = 0; // 记录匹配?的次序
		while (matcher.find()) {
			if (matcher.group().equals("'")) {
				isSurroundedBySingleQuotes = !isSurroundedBySingleQuotes;
			} else if (!isSurroundedBySingleQuotes && matcher.group().equals("?")) {
				matcher.appendReplacement(newSql, ":".concat(indexParameters.get(index)));
				index++;
			}
		}
		return matcher.appendTail(newSql).toString();
	}

	private void setQueryParameters(Query query, QueryParamList queryParamList) {
		if (queryParamList != null && queryParamList.size() > 0) {
			for (QueryParam queryParam : queryParamList.getQueryParams()) {
				query.setParameter(queryParam.getName(), queryParam.getValue());
			}
		}
	}

	@Override
	public <T> List<T> find(String qlString, QueryParamList queryParamList) throws JPAException {
		return this.find(qlString, queryParamList, null, null);
	}

	@Override
	public List<Object> findByNativeQuery(String sqlString) throws JPAException {
		return this.findByNativeQuery(sqlString, null, null);
	}

	@Override
	public List<Object> findByNativeQuery(String sqlString, QueryParamList queryParamList) throws JPAException {
		return this.findByNativeQuery(sqlString, queryParamList, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findByNativeQuery(String sqlString, QueryParamList queryParamList, PageInfo pageInfo)
			throws JPAException {

		Query query = this.em.createNativeQuery(sqlString);

		setQueryParameters(query, queryParamList);

		if (pageInfo != null) {

			// Set pagination for query
			int curPageNum = pageInfo.getCurPageNum();
			int rowOfPage = pageInfo.getRowOfPage();
			query.setFirstResult((curPageNum - 1) * rowOfPage).setMaxResults(rowOfPage);

			// Set allRowNum for PageInfo
			String queryCount = " SELECT COUNT(*) FROM (" + sqlString + ") TMP_COUNT_T ";
			int allRowNum = Integer.valueOf(this.em.createNativeQuery(queryCount).getSingleResult().toString());
			pageInfo.setAllRowNum(allRowNum);
		}

		return query.getResultList();
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.em.getCriteriaBuilder();
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
		return this.em.createNamedStoredProcedureQuery(name);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
