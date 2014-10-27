package org.kay.service;

public interface MenuService {
	public Object query(String id) throws Exception;
	public Object addNode() throws Exception;
	public Object save(String inserted, String updated, String deleted) throws Exception;
	public Object delete(String id) throws Exception;
}
