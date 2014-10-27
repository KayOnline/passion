package org.kay.framework.persistence.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.kay.framework.persistence.exception.JPAException;
import org.kay.framework.persistence.model.PageInfo;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.model.SortParamList;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BaseDao {
	
	public EntityManager getEntityManager() throws JPAException;
	
	public JdbcTemplate getJdbcTemplate();

	public <T> void persist(T entity) throws JPAException;

	public <T> T merge(T entity) throws JPAException;

	public <T> void remove(Class<T> entityClass, Object primaryKey) throws JPAException;

	public <T> T getReference(Class<T> entityClass, Object primaryKey) throws JPAException;

	public void flush() throws JPAException;

	public void setFlushMode(FlushModeType flushMode) throws JPAException;

	public FlushModeType getFlushMode() throws JPAException;

	public void refresh(Object entity) throws JPAException;

	public void detach(Object entity) throws JPAException;

	public boolean contains(Object entity) throws JPAException;

	public LockModeType getLockMode(Object entity) throws JPAException;

	public <T> T find(Class<T> entityClass, Object primaryKey) throws JPAException;
	
	public <T> List<T> find(String qlString, QueryParamList queryParamList) throws JPAException;
	
	public <T> List<T> find(String qlString, QueryParamList queryParamList, PageInfo pageInfo, SortParamList sortParamList) throws JPAException;
	
	public List<Object> findByNativeQuery(String sqlString) throws JPAException;
	
	public List<Object> findByNativeQuery(String sqlString, QueryParamList queryParamList) throws JPAException;
	
	public List<Object> findByNativeQuery(String sqlString, QueryParamList queryParamList, PageInfo pageInfo) throws JPAException;
	
	public int executeUpdate(String qlString, QueryParamList queryParamList) throws JPAException;
	
	public long getTotalCount(String qlString, QueryParamList queryParamList) throws JPAException;
	
	public CriteriaBuilder getCriteriaBuilder();
	
	public StoredProcedureQuery createNamedStoredProcedureQuery(String name);
	
}
