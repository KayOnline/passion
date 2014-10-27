package org.kay.framework.dataset.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.kay.framework.dataset.DBQuery;
import org.kay.framework.dataset.DBQueryList;

public abstract class DataSetUtils {

	private static final Logger logger = Logger.getLogger(DataSetUtils.class);

	public static Class<?> classForName(String javaType) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(javaType);
		} catch (ClassNotFoundException e) {
			logger.error("Java type : " + javaType + " not found!", e);
		}
		return clazz;
	}

	// FIXME 
	// 1.递归解析dbquery目录下的所有xml文件后加载到内存
	// 2.每次根据数据集名称去动态查找DBQuery节点
	// 3.不同的XML文件中可能存在相同的数据集名称【考虑数据集名称=过程函数名，避免重名情况】
	// 应该在程序启动的时候就解析数据集并存入内存中,或者采用多线程的方式去临时检索
	// 多各文件为避免不同文件的queryName重复，需要考虑使用命名空间
	public static DBQuery locateDBQuery(String queryName) {
		try {
			// XML2DBQueryList
			JAXBContext context = JAXBContext.newInstance(DBQueryList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			List<File> fileList = new ArrayList<File>();
			traverseXml("D:/Workspaces/passion/src/main/resources/dbquery/procdataset", fileList);
			for (File file : fileList) {
				DBQueryList dBQueryList = (DBQueryList) unmarshaller.unmarshal(file);
				for (DBQuery dBQuery : dBQueryList.getdBQuerys()) {
					if (dBQuery.getQueryName().equals(queryName)) {
						return dBQuery;
					}
				}
			}
			throw new Exception("DBQuery " + queryName + " not found!");
		} catch (Exception e) {
			logger.error("Locate DBQuery " + queryName + " failure!", e);
		}
		return null;
	}
	
	private static void traverseXml(String rootPath, List<File> list) {
		try {
			File file = new File(rootPath);
			if (file.exists()) {
				if(file.isDirectory()) { 
					for (File son : file.listFiles()) {
						traverseXml(son.getAbsolutePath(), list);
					}
				} else {
					list.add(file);
				}
			} else {
				file.mkdirs();
			}
		} catch (Exception e) {
			logger.error("Traverse config xml failure!", e);
		}
	}
}
