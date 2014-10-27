package org.kay.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.kay.framework.model.easyui.DataWrap;
import org.kay.framework.model.easyui.ResponseData;
import org.kay.framework.util.ReflectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseController {

	/** 传输数据用. */
	protected Map<String, String> property = new HashMap<String, String>();

	// 封装返回页面数据
	protected ResponseData responseData = new ResponseData();
	
	public abstract String init() throws Exception;

	
	//处理页面的上传数据
	@ModelAttribute
	public <T> DataWrap<T> addModelAttributes(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "inserted", required = false) String inserted, 
			@RequestParam(value = "updated", required = false) String updated,
			@RequestParam(value = "deleted", required = false) String deleted) 
			throws JsonParseException, JsonMappingException, IOException {
		
		// Construct DataWrap Object
		DataWrap<T> dataWrap = new DataWrap<T>();
		String methodName = "get" + StringUtils.capitalize("dataWrap");
		Class<? extends BaseController> clazz = this.getClass();
		Method method = ReflectUtils.getMethod(clazz, methodName, new Class[] {});
		if (method == null) {
			return null;
		}
		Type genericType = method.getGenericReturnType();
		Class<?> genericClass = null;
		if(genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType)genericType;
			Type resultType = type.getActualTypeArguments()[0];
            if(resultType instanceof ParameterizedType)
                genericClass = (Class<?>)((ParameterizedType)resultType).getRawType();
            else
                genericClass = (Class<?>)resultType;
		}
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		//objectMapper.configure(DeserializationConfig.Feature., state)
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		//objectMapper.setDateFormat(SimpleDateFormat.getDateTimeInstance());
		//objectMapper.setD
		TypeFactory typeFactory = TypeFactory.defaultInstance();
		CollectionType collectionType = typeFactory.constructCollectionType(List.class, genericClass);
		
		if (!StringUtils.isEmpty(page)) {
			dataWrap.getPageInfo().setCurPageNum(Integer.parseInt(page));
		}

		if (!StringUtils.isEmpty(rows)) {
			dataWrap.getPageInfo().setRowOfPage(Integer.parseInt(rows));
		}

		if (!StringUtils.isEmpty(inserted)) {
			List<T> insertedList = objectMapper.readValue(inserted, collectionType);
			dataWrap.setInsertedList(insertedList);
		}

		if (!StringUtils.isEmpty(updated)) {
			List<T> updatedList = objectMapper.readValue(updated, collectionType);
			dataWrap.setUpdatedList(updatedList);
		}

		if (!StringUtils.isEmpty(deleted)) {
			List<T> deletedList = objectMapper.readValue(deleted, collectionType);
			dataWrap.setDeletedList(deletedList);
		}
		
		if (!StringUtils.isEmpty(query)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>)objectMapper.readValue(query, Map.class);
			for(String key : map.keySet()) {
				dataWrap.getQueryParamList().addParam(key, map.get(key));
			}
		}
		
		String methodName1 = "set" + StringUtils.capitalize("dataWrap");
		Class<? extends BaseController> clazz1 = this.getClass();
		Method method1 = ReflectUtils.getMethod(clazz1, methodName1, new Class[] { DataWrap.class });
		ReflectUtils.invokeMethod(method1, this, dataWrap);
		
		return dataWrap;
	}
	

	public Map<String, String> getProperty() {
		return property;
	}

	public void setProperty(Map<String, String> property) {
		this.property = property;
		
	}
}
