package org.kay.framework.model.service;

import java.util.List;

/*
 * 用于构建树的接口
 */
public interface ITreeRetriever<T> {
	public abstract List<T> createRootNode();

	public abstract List<T> retrieveChildren(String code);

	public abstract boolean hasChild(String code);
}
