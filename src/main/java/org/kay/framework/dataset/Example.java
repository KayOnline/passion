package org.kay.framework.dataset;

import java.util.List;
import org.junit.Test;
import org.kay.framework.test.BaseSpringTestSupport;
/**
 * ----------DONE-----------------
 * 1、支持Oracle的过程\函数调用
 * ----------NOT START------------
 * 2、支持MySQL的过程、函数调用
 * 3、支持MSSQL的过程、函数调用
 * 4、查询数据集暂时不考虑实现
 * 
 * @author Kay
 *
 */
public class Example extends BaseSpringTestSupport {
	

	@Test
	public void testOracleProc() {
		Object[] params = new Object[3];
		params[0] = "";
		params[1] = "A1";
		params[2] = "A2";
		List<Object> resultList;
		DSTool tool = DSTool.getInstance();
		resultList = tool.executeProcOrFunc("FUNC_TEST", params);
		System.out.println(resultList.size());
	}
	
	
}
