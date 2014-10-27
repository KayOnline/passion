package org.kay.framework.dataset;

import org.apache.log4j.Logger;
import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.junit.Test;
import org.kay.framework.dataset.util.DataSetUtils;
import org.kay.framework.dataset.util.JdbcTypesUtils;
import org.kay.framework.test.BaseSpringTestSupport;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

public class DataSetApplication extends BaseSpringTestSupport {
	
	private static final Logger logger = Logger.getLogger(DataSetApplication.class);
	
	 @Test
	// 根据用户输入的信息生成DataSet XML文件
	public void coreApp() throws Exception {
		 
		 String filePath = "D:/Workspaces/passion/src/main/resources/dbquery/dataset.xml";
		 
		 JAXBContext context = JAXBContext.newInstance(DBQueryList.class);
		 Marshaller marshaller = context.createMarshaller();
		 Unmarshaller unmarshaller = context.createUnmarshaller();

		// 添加数据集（用户输入信息）
		DBQuery dBQuery = new DBQuery();
		dBQuery.setQueryDbType(DataSetConstant.DBTYPE_ORALCE); 	// 数据集的数据库
		dBQuery.setQueryType(DataSetConstant.FUNC_DATASET);		// 数据集的类型
		dBQuery.setQueryObject("TEST");						// 数据集对象
		dBQuery.setQueryAuthor("KAY"); 							// 数据集作者
		dBQuery.setQueryDescription("检索基本信息"); 				// 数据集描述
		
		// 校验存储过程
		String resultInfo = validateProcedures(dBQuery);
		if (!DataSetConstant.SUCCESS.equals(resultInfo)) {
			throw new Exception(resultInfo);
		}

		// 设置数据集标识、参数、语句
		setQueryName(dBQuery);
		setQueryParameterList(dBQuery);
		setPrepareQuery(dBQuery);
		
		// 添加至数据集列表
		DBQueryList dBQueryList = new DBQueryList();
		dBQueryList.appendDBQuery(dBQuery);
		
		// DBQueryList2XML
		File file = new File(filePath);
		marshaller.marshal(dBQueryList, file);
		logger.info("数据集META文件创建成功");
	}
	 
	private void setQueryName(DBQuery dBQuery) {
		if (DataSetConstant.PROC_DATASET.equals(dBQuery.getQueryType())) {
			dBQuery.setQueryName("PROC_" + dBQuery.getQueryObject().toUpperCase());
		} else if (DataSetConstant.FUNC_DATASET.equals(dBQuery.getQueryType())) {
			dBQuery.setQueryName("FUNC_" + dBQuery.getQueryObject().toUpperCase());
		} else if (DataSetConstant.QUERY_DATASET.equals(dBQuery.getQueryType())) {
			// 注意：基础查询的数据集名称需要用户录入
			dBQuery.setQueryName("QUERY_" + dBQuery.getQueryName().toUpperCase());
		}
	}
	
	private String validateProcedures(DBQuery dBQuery) {
		if (DataSetConstant.PROC_DATASET.equals(dBQuery.getQueryType())
				|| DataSetConstant.FUNC_DATASET.equals(dBQuery.getQueryType())) {
			if (DataSetConstant.DBTYPE_ORALCE.equals(dBQuery.getQueryDbType())) {
				return validateORACLEProcedures(dBQuery);
			} else if (DataSetConstant.DBTYPE_MYSQL.equals(dBQuery.getQueryDbType())) {
				return validateMYSQLProcedures(dBQuery);
			} else if (DataSetConstant.DBTYPE_MSSQL.equals(dBQuery.getQueryDbType())) {
				return validateMSSQLProcedures(dBQuery);
			}
		}
		return DataSetConstant.SUCCESS;
	}

	private String validateMSSQLProcedures(DBQuery dBQuery) {
		// TODO 校验MSSQL存储过程尚未实现
		return null;
	}

	private String validateMYSQLProcedures(DBQuery dBQuery) {
		// TODO 校验MYSQL存储过程尚未实现
		return null;
	}

	private String validateORACLEProcedures(final DBQuery dBQuery) {
		String resultInfo = DataSetConstant.SUCCESS;
		String querySQL = " SELECT S.OBJECT_NAME, S.OBJECT_TYPE, S.STATUS 		" 
				        + "   FROM USER_OBJECTS S 								" 
				        + "  WHERE S.OBJECT_TYPE IN ('FUNCTION', 'PROCEDURE') 	" 
				        + "    AND S.OBJECT_NAME = ? 							";
		List<DynaBean> dynaBeanList = this.baseDao.getJdbcTemplate().execute(querySQL, new PreparedStatementCallback<List<DynaBean>>() {
			@Override
			@SuppressWarnings("unchecked")
			public List<DynaBean> doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {
				List<DynaBean> dynaBeanList = new ArrayList<DynaBean>();
				pstmt.setObject(1, dBQuery.getQueryObject(), java.sql.Types.VARCHAR);
				ResultSet rs = pstmt.executeQuery();
				RowSetDynaClass rsdc = new RowSetDynaClass(rs, false);
				dynaBeanList = rsdc.getRows();
				rs.close();
				return dynaBeanList;
			}
		});
		
		// 1.校验检索对象是否存在
		// 2.校验检索的过程函数是否失效
		if (dynaBeanList.isEmpty()) {
			resultInfo = "Procedure named " + dBQuery.getQueryObject() + " not found!";
		} else {
			DynaBean dynaBean = dynaBeanList.get(0);
			String queryObjectStatus = dynaBean.get("STATUS") + "";
			if (DataSetConstant.OBJECT_STATUS_INVALID.equals(queryObjectStatus)) {
				resultInfo = "Procedure named " + dBQuery.getQueryObject() + " is invalid!";
			}
		}
		
		return resultInfo;
	}
	
	private void setPrepareQuery(DBQuery query) {
		// isOracleProcDataSet
		StringBuffer buffer = new StringBuffer(16);
		if (DataSetConstant.DBTYPE_ORALCE.equals(query.getQueryDbType())) {
			if (DataSetConstant.PROC_DATASET.equals(query.getQueryType())) {
				buffer.append("{");
				buffer.append(" call ");
				buffer.append(query.getQueryObject().toUpperCase());
				buffer.append("(");
				for (int i = 0; i < query.getQueryParameterList().size(); i++) {
					buffer.append("?");
					if (i + 1 < query.getQueryParameterList().size()) {
						buffer.append(", ");
					}
				}
				buffer.append(")");
				buffer.append(" ");
				buffer.append("}");
			} else if (DataSetConstant.FUNC_DATASET.equals(query.getQueryType())) {
				buffer.append("{");
				buffer.append(" ? = call ");
				buffer.append(query.getQueryObject().toUpperCase());
				buffer.append("(");
				for (int i = 1; i < query.getQueryParameterList().size(); i++) {
					buffer.append("?");
					if (i + 1 < query.getQueryParameterList().size()) {
						buffer.append(", ");
					}
				}
				buffer.append(")");
				buffer.append(" ");
				buffer.append("}");
			}
		}
		query.setPrepareQuery(buffer.toString());
	}

	private void setQueryParameterList(final DBQuery dBQuery) {
		if (DataSetConstant.QUERY_DATASET.equals(dBQuery.getQueryType())) {
			setQueryParameterListForQuery(dBQuery);
		} else if (DataSetConstant.PROC_DATASET.equals(dBQuery.getQueryType())
				|| DataSetConstant.FUNC_DATASET.equals(dBQuery.getQueryType())) {
			setQueryParameterListForProcedures(dBQuery);
		}
	}
	
	// 设置基础查询的参数列表
	private void setQueryParameterListForQuery(DBQuery dBQuery) {
		// TODO 查询数据的参数处理尚未实现
	}

	// 设置存储过程的参数列表
	private void setQueryParameterListForProcedures(DBQuery dBQuery) {
		if (DataSetConstant.DBTYPE_ORALCE.equals(dBQuery.getQueryDbType())) {
			setQueryParameterListForORACLEProcedures(dBQuery);
		} else if (DataSetConstant.DBTYPE_MYSQL.equals(dBQuery.getQueryDbType())) {
			setQueryParameterListForMYSQLProcedures(dBQuery);
		} else if (DataSetConstant.DBTYPE_MSSQL.equals(dBQuery.getQueryDbType())) {
			setQueryParameterListForMSSQLProcedures(dBQuery);
		}
	}

	// 设置MSSQL存储过程参数列表
	private void setQueryParameterListForMSSQLProcedures(DBQuery dBQuery) {
		// TODO MSSQL存储过程的参数列表尚未实现
	}

	// 设置MYSQL存储过程参数列表
	private void setQueryParameterListForMYSQLProcedures(DBQuery dBQuery) {
		// TODO MYSQL存储过程的参数列表尚未实现
	}

	// 设置ORACLE存储过程参数列表
	private void setQueryParameterListForORACLEProcedures(final DBQuery dBQuery) {
		String querySQL = " SELECT S.SEQUENCE, S.ARGUMENT_NAME, S.DATA_TYPE, S.IN_OUT " 
						+ "   FROM USER_ARGUMENTS S " 
						+ "  WHERE S.OBJECT_NAME = ? ";
		List<DynaBean> dynaBeanList = this.baseDao.getJdbcTemplate().execute(querySQL, new PreparedStatementCallback<List<DynaBean>>() {
			@Override
			@SuppressWarnings("unchecked")
			public List<DynaBean> doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {
				List<DynaBean> dynaBeanList = new ArrayList<DynaBean>();
				pstmt.setObject(1, dBQuery.getQueryObject(), java.sql.Types.VARCHAR);
				ResultSet rs = pstmt.executeQuery();
				RowSetDynaClass rsdc = new RowSetDynaClass(rs, false);
				dynaBeanList = rsdc.getRows();
				rs.close();
				return dynaBeanList;
			}
		});
		List<QueryParameter> queryParameterList = new ArrayList<QueryParameter>();
		for (DynaBean dynaBean : dynaBeanList) {
			int sequence = Integer.parseInt(dynaBean.get("SEQUENCE") + "");
			String argumentName = dynaBean.get("ARGUMENT_NAME") + "";
			String inOut = dynaBean.get("IN_OUT") + "";
			String dataType = dynaBean.get("DATA_TYPE") + "";
			String javaType = getJavaType(dataType).getName();
			int jdbcType = JdbcTypesUtils.convertJava2jdbcTypes(DataSetUtils.classForName(javaType));
			QueryParameter queryParameter = new QueryParameter();
			queryParameter.setSequence(sequence);
			queryParameter.setArgumentName(argumentName);
			queryParameter.setInOut(inOut);
			queryParameter.setDataType(dataType);
			queryParameter.setJavaType(javaType);
			queryParameter.setJdbcType(jdbcType);
			queryParameterList.add(queryParameter);
		}
		dBQuery.setQueryParameterList(queryParameterList);
	}

	private Class<?> getJavaType(String oracleType) {
		if (oracleType.indexOf("VARCHAR") > -1) {
			return String.class;
		} else if (oracleType.indexOf("LONG") > -1) {
			return String.class;
		} else if (oracleType.indexOf("CHAR") > -1) {
			return String.class;
		} else if (oracleType.indexOf("TIMESTAMP") > -1) {
			return Timestamp.class;
		} else if (oracleType.indexOf("DATE") > -1) {
			return Timestamp.class;
		} else if (oracleType.indexOf("NUMBER") > -1) {
			return BigDecimal.class;
		} else if (oracleType.indexOf("REF CURSOR") > -1){
			return Object.class;
		} else {
			return Object.class;
		}
	}
}
