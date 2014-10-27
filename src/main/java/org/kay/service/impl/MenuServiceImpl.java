package org.kay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kay.entity.SysResource;
import org.kay.framework.model.easyui.TreeNode;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=false)
	public Object query(String id) throws Exception {
		String qlString = "";
		QueryParamList params = new QueryParamList();

		if (StringUtils.isEmpty(id)) {
			qlString = " from SysResource f where f.sysResource is null ";
		} else {
			qlString = " from SysResource f where f.sysResource.resourceId = :id ";
			params.addParam("id", id);
		}
		List<SysResource> sysResouces = baseDao.find(qlString, params, null, null);

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int i = 0; sysResouces != null && i < sysResouces.size(); i++) {

			TreeNode node = new TreeNode();
			node.setId(sysResouces.get(i).getResourceId());
			node.setText(sysResouces.get(i).getResourceName());

			// 用于页面的显示field:'url'
			node.setUrl(sysResouces.get(i).getResourceUrl());
			// 用于节点对象获取属性node.attributes.url，直接使用node.url是无法获取的
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", sysResouces.get(i).getResourceUrl());
			node.setAttributes(attributes);

			if (sysResouces.get(i).getSysResources() != null && !sysResouces.get(i).getSysResources().isEmpty()) {
				node.setState("closed");
			} else {
				node.setState("open");
			}
			nodes.add(node);
		}
		return nodes;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=false)
	public Object addNode() throws Exception {
		Long sequenceNo = 123L;//this.baseDao.genEntitySequenceByCenterTable(SysResource.class);
		return sequenceNo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=false)
	public Object save(String inserted, String updated, String deleted) throws Exception {

		if (updated != null && !updated.trim().equals("")) {
			ObjectMapper mapper = new ObjectMapper();
			TreeNode[] easyuiTreeNodes = mapper.readValue(updated, TreeNode[].class);

			for (int i = 0; i < easyuiTreeNodes.length; i++) {

				SysResource sr = new SysResource();

				sr.setResourceId(easyuiTreeNodes[i].getId());
				sr.setResourceName(easyuiTreeNodes[i].getText());
				sr.setResourceUrl(easyuiTreeNodes[i].getUrl());
				
				SysResource psr = baseDao.getReference(SysResource.class, easyuiTreeNodes[i].get_parentId());

				sr.setResourceType("M");

				sr.setSysResource(psr);

				this.baseDao.merge(sr);
			}

		}
		return "success";
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false)
	public Object delete(String id) throws Exception {
		if (!StringUtils.isEmpty(id)) {
			this.baseDao.remove(SysResource.class, id);
		}
		return "success";
	}

}
