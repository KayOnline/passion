package org.kay.framework.dataset;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.kay.framework.dataset.exception.DataSetException;
import org.kay.framework.dataset.util.DataSetUtils;
import org.kay.framework.persistence.util.BeanLocator;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

public class DSTool {
	
	private JdbcTemplate jdbcTemplate;
	
	private static DSTool me;
	
	public static DSTool getInstance() {
		if (me == null) {
			me = new DSTool();
		}
		me.jdbcTemplate = BeanLocator.getBean("jdbcTemplate", JdbcTemplate.class);
		return me;
	}
	
	public List<Object> executeProcOrFunc(String dataSetName, final Object... params) {
		List<Object> resultList = new ArrayList<Object>();

		// 1.Locate the specific named dataSet file
		final DBQuery dBQuery = DataSetUtils.locateDBQuery(dataSetName);

		// 2.Check whether parameter valid or not
		int prepareParamsNum = dBQuery.getQueryParameterList().size();
		int realParamsNum = params.length;
		if (prepareParamsNum != realParamsNum) {
			throw new DataSetException(" Expected params num is " + prepareParamsNum + ", real num is " + realParamsNum);
		}
		if (prepareParamsNum > 0) {
			List<QueryParameter> queryParameterList = dBQuery.getQueryParameterList();
			for (int i = 0; i < queryParameterList.size(); i++) {
				String javaType = queryParameterList.get(i).getJavaType();
				Class<?> clazz = DataSetUtils.classForName(javaType);
				if (!clazz.equals(params[i].getClass())) {
					throw new DataSetException("The paramter indexed " + i + " type invalid. Expected type " + javaType
							+ " rather then " + params[i].getClass().getName() + ".");
				}
			}
		}

		// 3.Invoke the procedure or function
		resultList = jdbcTemplate.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection conn) throws SQLException {
				CallableStatement cs = conn.prepareCall(dBQuery.getPrepareQuery());
				for (QueryParameter queryParameter : dBQuery.getQueryParameterList()) {

					int parameterIndex = queryParameter.getSequence();
					Object value = params[parameterIndex - 1];
					int targetSqlType = queryParameter.getJdbcType();

					if (queryParameter.getInOut().indexOf("IN") > -1) {
						cs.setObject(parameterIndex, value, targetSqlType);
					}

					if (queryParameter.getInOut().indexOf("OUT") > -1) {
						cs.registerOutParameter(parameterIndex, targetSqlType);
					}

				}
				return cs;
			}
		}, new CallableStatementCallback<List<Object>>() {
			@Override
			public List<Object> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				List<Object> list = new ArrayList<Object>();
				for (QueryParameter param : dBQuery.getQueryParameterList()) {
					if (param.getInOut().indexOf("OUT") > -1) {
						if (param.getDataType().indexOf("REF CURSOR") > -1) {
							ResultSet rs = (ResultSet) cs.getObject(param.getSequence());
							RowSetDynaClass rsdc = new RowSetDynaClass(rs, false);
							list.add(rsdc.getRows());
							rs.close();
						} else {
							Object obj = cs.getObject(param.getSequence());
							list.add(obj);
						}
					}
				}
				return list;
			}
		});
		return resultList;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
