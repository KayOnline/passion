package org.kay.framework.persistence.dao.impl;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Ignore;
import org.junit.Test;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.model.SortParam;
import org.kay.framework.persistence.model.SortParamList;
import org.kay.framework.test.BaseSpringTestSupport;
import org.springframework.util.StringUtils;

public class BaseDaoImplTest extends BaseSpringTestSupport {

	public void testFindByNativeQueryStringQueryParamListPageInfo() {
		String sqlString = " SELECT SYSDATE FROM DUAL ";
		List<Object> list = this.getBaseDao().findByNativeQuery(sqlString, null, null);
		if (list != null && list.size() > 0) {
			Object obj = list.get(0);
			assertEquals(true, obj instanceof Timestamp);
		} else {
			fail("Could not retrieve current datetime from datebase.");
		}
	}

	@Test
	@Ignore
	public void testMultiParams() {
		String qlString = " select s from Organization s where s.groupName = :groupName ";
		QueryParamList params = new QueryParamList();
		params.addParam("groupName", "广州分公司");
		List<Object> list = this.baseDao.find(qlString, params);
		System.out.println(list.size());
	}

	@Test
	public void testRegex() {
		String qlString = " select s from Organization s where s.partyId = ? "
				+ "and s.partyId = '?' and s.partyId = ? order by s.name";
		List<String> indexParameters = new ArrayList<String>();
		indexParameters.add("partyId");
		indexParameters.add("partyId");
		indexParameters.add("partyId");
		String result = this.convertIndexed2Named(qlString, indexParameters);
		// System.out.println(result);

	}

	// select s from Organization s where s.partyId = :partyId and s.partyId =
	// '?' and s.partyId = :partyId

	private String convertIndexed2Named(String qlString, List<String> indexParameters) {
		StringBuffer buffer = new StringBuffer();
		Pattern pattern = Pattern.compile("[']|[?]");
		Matcher matcher = pattern.matcher(qlString);
		boolean isSurroundedBySingleQuotes = false; // 用于忽略单引号所包含的内容
		int index = 0; // 记录匹配?的次序
		boolean added = false;
		while (matcher.find()) {
			added = false;
			if (matcher.group().equals("'")) {
				isSurroundedBySingleQuotes = !isSurroundedBySingleQuotes;
			} else if (!isSurroundedBySingleQuotes && matcher.group().equals("?")) {
				matcher.appendReplacement(buffer, ":".concat(indexParameters.get(index)));
				index++;
				added = true;
			}
			// if (!added) {
			// System.out.println(matcher.group());
			// matcher.appendReplacement(newSql, matcher.group());
			// }
		}
		// System.out.println(newSql);
		System.out.println(matcher.appendTail(buffer).toString());
		return matcher.appendTail(buffer).toString();
	}

	@Test
	public void testSetSortOptions() {
		String jpqlString ="select 1 from user s order by s.name";
		SortParamList sortParamList = new SortParamList();
		sortParamList.addParam("s.age", "DESC");
		System.out.println(this.setSortOptions(jpqlString,sortParamList));
	}

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
		}
		return jpqlString.substring(0, jpqlString.length() - 1);
	}

}
