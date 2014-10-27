package org.kay.framework.dataset.util;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

public abstract class JdbcTypesUtils {

	private static Map<Class<?>, Integer> java2jdbcTypes;
	private static Map<Integer, Class<?>> jdbc2JavaTypes;

	public static Integer convertJava2jdbcTypes(Class<?> key) {
		return java2jdbcTypes.get(key);
	}

	public static Class<?> convertJdbc2JavaTypes(Integer key) {
		return jdbc2JavaTypes.get(key);
	}

	static {
		java2jdbcTypes = new HashMap<Class<?>, Integer>();
		jdbc2JavaTypes = new HashMap<Integer, Class<?>>();
		
		// JDBC2JAVA
		jdbc2JavaTypes.put(Types.LONGNVARCHAR, String.class);
		jdbc2JavaTypes.put(Types.NCHAR, String.class);
		jdbc2JavaTypes.put(Types.NVARCHAR, String.class);
		jdbc2JavaTypes.put(Types.ROWID, String.class);
		jdbc2JavaTypes.put(Types.BIT, Boolean.class);
		jdbc2JavaTypes.put(Types.TINYINT, Byte.class);
		jdbc2JavaTypes.put(Types.BIGINT, Long.class);
		jdbc2JavaTypes.put(Types.LONGVARBINARY, Blob.class);
		jdbc2JavaTypes.put(Types.VARBINARY, Blob.class);
		jdbc2JavaTypes.put(Types.BINARY, Blob.class);
		jdbc2JavaTypes.put(Types.LONGVARCHAR, String.class);
		jdbc2JavaTypes.put(Types.NULL, String.class);
		jdbc2JavaTypes.put(Types.CHAR, String.class);
		jdbc2JavaTypes.put(Types.NUMERIC, BigDecimal.class);
		jdbc2JavaTypes.put(Types.DECIMAL, BigDecimal.class);
		jdbc2JavaTypes.put(Types.INTEGER, Integer.class);
		jdbc2JavaTypes.put(Types.SMALLINT, Short.class);
		jdbc2JavaTypes.put(Types.FLOAT, BigDecimal.class);
		jdbc2JavaTypes.put(Types.REAL, BigDecimal.class);
		jdbc2JavaTypes.put(Types.DOUBLE, BigDecimal.class);
		jdbc2JavaTypes.put(Types.VARCHAR, String.class);
		jdbc2JavaTypes.put(Types.BOOLEAN, Boolean.class);
		jdbc2JavaTypes.put(Types.DATALINK, String.class);
		jdbc2JavaTypes.put(Types.DATE, Date.class);
		jdbc2JavaTypes.put(Types.TIME, Date.class);
		jdbc2JavaTypes.put(Types.TIMESTAMP, Timestamp.class);
		jdbc2JavaTypes.put(Types.OTHER, Object.class);
		jdbc2JavaTypes.put(Types.JAVA_OBJECT, Object.class);
		jdbc2JavaTypes.put(Types.DISTINCT, String.class);
		jdbc2JavaTypes.put(Types.STRUCT, String.class);
		jdbc2JavaTypes.put(Types.ARRAY, String.class);
		jdbc2JavaTypes.put(Types.BLOB, Blob.class);
		jdbc2JavaTypes.put(Types.CLOB, Clob.class);
		jdbc2JavaTypes.put(Types.REF, String.class);
		jdbc2JavaTypes.put(Types.SQLXML, String.class);
		jdbc2JavaTypes.put(Types.NCLOB, Clob.class);

		// JAVA2JDBC
		java2jdbcTypes.put(java.lang.String.class, Types.VARCHAR);
		java2jdbcTypes.put(java.math.BigDecimal.class, Types.NUMERIC);
		java2jdbcTypes.put(java.lang.Boolean.class, Types.BIT);
		java2jdbcTypes.put(java.lang.Integer.class, Types.INTEGER);
		java2jdbcTypes.put(java.lang.Long.class, Types.BIGINT);
		java2jdbcTypes.put(java.lang.Float.class, Types.REAL);
		java2jdbcTypes.put(java.lang.Double.class, Types.DOUBLE);
		java2jdbcTypes.put(java.lang.Byte[].class, Types.VARBINARY);
		java2jdbcTypes.put(java.sql.Date.class, Types.DATE);
		java2jdbcTypes.put(java.util.Date.class, Types.DATE);
		java2jdbcTypes.put(java.sql.Time.class, Types.TIME);
		java2jdbcTypes.put(java.sql.Timestamp.class, Types.TIMESTAMP);
		java2jdbcTypes.put(java.lang.Object.class, OracleTypes.CURSOR);

	}
}
